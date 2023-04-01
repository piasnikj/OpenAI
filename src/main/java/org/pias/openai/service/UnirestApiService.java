package org.pias.openai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;
import org.pias.openai.Config;
import org.pias.openai.service.chat.payload.ChatCompletionRequest;
import org.pias.openai.service.chat.response.ChatCompletionResponse;

public class UnirestApiService implements ApiService {

    @Override
    public ChatCompletionResponse chat(ChatCompletionRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<JsonNode> response = createPostRequest(CHAT_COMPLETIONS, mapper.writeValueAsString(request)).asJson();

        if (!response.isSuccess()) {
            throw new RuntimeException("API Request failed: + " + response.getStatusText());
        }

        return mapper.readValue(response.getBody().toString(), ChatCompletionResponse.class);
    }

    private RequestBodyEntity createPostRequest(String endpoint,
                                                String body) {
        return Unirest.post(OPENAI_API_BASE_URL + endpoint)
                .header(CONTENT_TYPE, APP_JSON)
                .header(AUTHORIZATION, BEARER + getApiKey())
                .body(body);
    }

    private String getApiKey() {
        String apiKey = Config.getInstance().getProperty("api_key");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("API key not configured");
        }

        return apiKey;
    }
}

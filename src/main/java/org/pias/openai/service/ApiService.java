package org.pias.openai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.pias.openai.service.chat.payload.ChatCompletionRequest;
import org.pias.openai.service.chat.response.ChatCompletionResponse;

public interface ApiService {
    String OPENAI_API_BASE_URL = "https://api.openai.com/v1/";
    String CHAT_COMPLETIONS = "chat/completions";
    String CONTENT_TYPE = "Content-Type";
    String APP_JSON = "application/json";
    String AUTHORIZATION = "Authorization";
    String BEARER = "Bearer ";

    ChatCompletionResponse chat(ChatCompletionRequest request) throws JsonProcessingException;
}

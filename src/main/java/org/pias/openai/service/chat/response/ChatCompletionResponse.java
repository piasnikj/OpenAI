package org.pias.openai.service.chat.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatCompletionResponse(@JsonProperty("id") String id,
                                     @JsonProperty("object") String object,
                                     @JsonProperty("created") Long created,
                                     @JsonProperty("model") String model,
                                     @JsonProperty("choices") List<Choice> choices,
                                     @JsonProperty("usage") Usage usage) {
}

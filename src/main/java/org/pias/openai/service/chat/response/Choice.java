package org.pias.openai.service.chat.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.pias.openai.service.chat.payload.Message;

public record Choice(@JsonProperty("index") Integer index,
                     @JsonProperty("message") Message message,
                     @JsonProperty("finish_reason") String finishReason) {
}

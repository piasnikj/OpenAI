package org.pias.openai.service.chat.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(@JsonProperty("role") String role,
                      @JsonProperty("content") String content) {

    public static class Builder {
        private String role;
        private String content;

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(role, content);
        }
    }
}

package org.pias.openai.service.chat.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatCompletionRequest(@JsonProperty("model") String model,
                                    @JsonProperty("messages") List<Message> messages,
                                    @JsonProperty("max_tokens") Integer maxTokens,
                                    @JsonProperty("temperature") Float temperature,
                                    @JsonProperty("n") Integer n,
                                    @JsonProperty("stop") List<String> stop) {

    public static class Builder {
        private String model;
        private List<Message> messages;
        private Integer maxTokens;
        private Float temperature;
        private Integer n;
        private List<String> stop;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder messages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public Builder maxTokens(Integer maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        public Builder temperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder n(Integer n) {
            this.n = n;
            return this;
        }

        public Builder stop(List<String> stop) {
            this.stop = stop;
            return this;
        }

        public ChatCompletionRequest build() {
            return new ChatCompletionRequest(model, messages, maxTokens, temperature, n, stop);
        }
    }
}

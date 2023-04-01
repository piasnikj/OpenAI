package org.pias.openai.util.enums;

import java.util.Arrays;
import java.util.List;

public enum ModelEnum {
    ADA ("ada"),
    ADA_CODE_SEARCH_CODE ("ada-code-search-code"),
    ADA_CODE_SEARCH_TEXT ("ada-code-search-text"),
    ADA_SEARCH_DOCUMENT ("ada-search-document"),
    ADA_SEARCH_QUERY ("ada-search-query"),
    ADA_SIMILARITY ("ada-similarity"),
    ADA2020_05_03 ("ada:2020-05-03"),
    BABBAGE ("babbage"),
    BABBAGE_CODE_SEARCH_CODE ("babbage-code-search-code"),
    BABBAGE_CODE_SEARCH_TEXT ("babbage-code-search-text"),
    BABBAGE_SEARCH_DOCUMENT ("babbage-search-document"),
    BABBAGE_SEARCH_QUERY ("babbage-search-query"),
    BABBAGE_SIMILARITY ("babbage-similarity"),
    BABBAGE2020_05_03 ("babbage:2020-05-03"),
    CODE_DAVINCI_EDIT_001 ("code-davinci-edit-001"),
    CODE_SEARCH_ADA_CODE_001 ("code-search-ada-code-001"),
    CODE_SEARCH_ADA_TEXT_001 ("code-search-ada-text-001"),
    CODE_SEARCH_BABBAGE_CODE_001 ("code-search-babbage-code-001"),
    CODE_SEARCH_BABBAGE_TEXT_001 ("code-search-babbage-text-001"),
    CURIE ("curie"),
    CURIE_INSTRUCT_BETA ("curie-instruct-beta"),
    CURIE_SEARCH_DOCUMENT ("curie-search-document"),
    CURIE_SEARCH_QUERY ("curie-search-query"),
    CURIE_SIMILARITY ("curie-similarity"),
    CURIE2020_05_03 ("curie:2020-05-03"),
    CUSHMAN2020_05_03 ("cushman:2020-05-03"),
    DAVINCI ("davinci"),
    DAVINCI_IF3_0_0 ("davinci-if:3.0.0"),
    DAVINCI_INSTRUCT_BETA ("davinci-instruct-beta"),
    DAVINCI_INSTRUCT_BETA2_0_0 ("davinci-instruct-beta:2.0.0"),
    DAVINCI_SEARCH_DOCUMENT ("davinci-search-document"),
    DAVINCI_SEARCH_QUERY ("davinci-search-query"),
    DAVINCI_SIMILARITY ("davinci-similarity"),
    DAVINCI2020_05_03 ("davinci:2020-05-03"),
    GPT_3_5_TURBO ("gpt-3.5-turbo"),
    GPT_3_5_TURBO_0301 ("gpt-3.5-turbo-0301"),
    IF_CURIE_V2 ("if-curie-v2"),
    IF_DAVINCI_V2 ("if-davinci-v2"),
    IF_DAVINCI3_0_0 ("if-davinci:3.0.0"),
    TEXT_ADA_001 ("text-ada-001"),
    TEXT_ADA001 ("text-ada:001"),
    TEXT_BABBAGE_001 ("text-babbage-001"),
    TEXT_BABBAGE001 ("text-babbage:001"),
    TEXT_CURIE_001 ("text-curie-001"),
    TEXT_CURIE001 ("text-curie:001"),
    TEXT_DAVINCI_001 ("text-davinci-001"),
    TEXT_DAVINCI_002 ("text-davinci-002"),
    TEXT_DAVINCI_003 ("text-davinci-003"),
    TEXT_DAVINCI_EDIT_001 ("text-davinci-edit-001"),
    TEXT_DAVINCI001 ("text-davinci:001"),
    TEXT_EMBEDDING_ADA_002 ("text-embedding-ada-002"),
    TEXT_SEARCH_ADA_DOC_001 ("text-search-ada-doc-001"),
    TEXT_SEARCH_ADA_QUERY_001 ("text-search-ada-query-001"),
    TEXT_SEARCH_BABBAGE_DOC_001 ("text-search-babbage-doc-001"),
    TEXT_SEARCH_BABBAGE_QUERY_001 ("text-search-babbage-query-001"),
    TEXT_SEARCH_CURIE_DOC_001 ("text-search-curie-doc-001"),
    TEXT_SEARCH_CURIE_QUERY_001 ("text-search-curie-query-001"),
    TEXT_SEARCH_DAVINCI_DOC_001 ("text-search-davinci-doc-001"),
    TEXT_SEARCH_DAVINCI_QUERY_001 ("text-search-davinci-query-001"),
    TEXT_SIMILARITY_ADA_001 ("text-similarity-ada-001"),
    TEXT_SIMILARITY_BABBAGE_001 ("text-similarity-babbage-001"),
    TEXT_SIMILARITY_CURIE_001 ("text-similarity-curie-001"),
    TEXT_SIMILARITY_DAVINCI_001 ("text-similarity-davinci-001"),
    WHISPER_1 ("whisper-1"),
    ;

    private final String model;

    ModelEnum(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public List<String> getModelNames() {
        return Arrays.stream(values()).map(ModelEnum::getModel).toList();
    }
}

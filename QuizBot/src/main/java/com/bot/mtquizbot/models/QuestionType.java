package com.bot.mtquizbot.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionType {

    @JsonProperty("type")
    private final QuestionTypeEnum type;

    @JsonProperty("name")
    private final String humanReadableName;

    @JsonProperty("description")
    private final String description;
}

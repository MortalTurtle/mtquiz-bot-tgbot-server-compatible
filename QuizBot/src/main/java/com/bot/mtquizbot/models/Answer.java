package com.bot.mtquizbot.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Answer {
    private final String questionId;
    private final String text;
}

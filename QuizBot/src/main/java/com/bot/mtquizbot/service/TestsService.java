package com.bot.mtquizbot.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.bot.mtquizbot.models.Answer;
import com.bot.mtquizbot.models.Test;
import com.bot.mtquizbot.models.TestResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestsService extends BaseService {

    public String create(String apiToken, String name, String description, Integer minScoreToBeat) {
        throw new UnsupportedOperationException();
    }

    public Test getById(String apiToken, String groupId, String id) {
        throw new UnsupportedOperationException();
    }

    public List<Test> getTestsForGroup(String apiToken, String groupId, Integer limit, Integer offset) {
        throw new UnsupportedOperationException();
    }

    public String getTestFullDescription(Test test) {
        return test.getName() + " - " + test.getDescription() + "\n" +
                (test.getMin_score() == null ? "" : "Min score to complete - " + Integer.toString(test.getMin_score()));
    }

    public void updateTest(String apiToken, String groupId,
        String newName, String newDescription, Integer newMinScore) {
        throw new UnsupportedOperationException();
    }

    public List<TestResult> getResultList(String apiToken, Optional<String> testId, Integer limit, Integer offset) {
        throw new UnsupportedOperationException();
    }

    public void submitResults(String apiToken, String testId, List<Answer> answers) {
        throw new UnsupportedOperationException();
    }

    public InlineKeyboardMarkup getEditMenu(Test test) {
        var editQuestionsButton = InlineKeyboardButton.builder()
                .callbackData("/editquestions " + test.getId())
                .text("Questions 📌").build();
        var menu = BaseService.getEditMenuBuilder(test, "/ststfield");
        menu.keyboardRow(List.of(editQuestionsButton));
        var backButton = InlineKeyboardButton.builder().callbackData("/test " + test.getId()).text("Back 🚫").build();
        menu.keyboardRow(List.of(backButton));
        return menu.build();
    }

    public String getMessageTextFromTestResults(Map<TestResult, Test> results) {
        String msgString = "";
        for (var result : results.keySet()) {
            var test = results.get(result);
            msgString += result.getScore().toString() + "/" +
                    test.getMin_score().toString() + " " +
                    "test name: " + test.getName() + "\n";
        }
        return msgString;
    }
}

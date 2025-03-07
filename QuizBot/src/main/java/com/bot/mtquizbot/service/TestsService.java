package com.bot.mtquizbot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.glassfish.grizzly.utils.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.bot.mtquizbot.models.Test;
import com.bot.mtquizbot.models.TestGroup;
import com.bot.mtquizbot.models.TestResult;
import com.bot.mtquizbot.models.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestsService extends BaseService {

    public Test create(User owner, TestGroup group, String name, Integer minScore, String description) {
        throw new UnsupportedOperationException();
    }

    public Test getById(String id) {
        throw new UnsupportedOperationException();
    }

    public List<Test> getTestList(TestGroup group) {
        throw new UnsupportedOperationException();
    }

    public String getTestFullDescription(Test test) {
        return test.getName() + " - " + test.getDescription() + "\n" +
                (test.getMin_score() == null ? "" : "Min score to complete - " + Integer.toString(test.getMin_score()));
    }

    public List<TestResult> getTestResultList(User user, Integer limit, Integer offset) {
        throw new UnsupportedOperationException();
    }

    public void putTestResult(User user, String testId, Integer score) {
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

    public void updateTestProperty(Test test, String propertyName, String strVal) throws NoSuchFieldException,
            IllegalArgumentException,
            NumberFormatException {
        throw new UnsupportedOperationException();
    }

    // TODO: make shorter
    public Pair<InlineKeyboardMarkup, String> getGroupTestsMenuWithDescription(TestGroup group,
            Integer maxTestButtonsInTestsMenuRow) {
        var tests = getTestList(group);
        StringBuilder strB = new StringBuilder();
        List<InlineKeyboardButton> testButtons = new ArrayList();
        var menu = InlineKeyboardMarkup.builder();
        int buttonsInRowleft = maxTestButtonsInTestsMenuRow;
        int cnt = 0;
        strB.append("your groups tests:\n");
        for (var test : tests) {
            cnt++;
            strB.append(Integer.toString(cnt) + "): " +
                    test.getName() + " - " +
                    test.getDescription() + "\n");
            buttonsInRowleft--;
            testButtons.add(
                    InlineKeyboardButton.builder()
                            .callbackData("/test " + test.getId())
                            .text(Integer.toString(cnt) + "✅")
                            .build());
            if (buttonsInRowleft == 0) {
                menu.keyboardRow(testButtons);
                testButtons = new ArrayList<>();
                buttonsInRowleft = maxTestButtonsInTestsMenuRow;
            }
        }
        if (buttonsInRowleft > 0)
            menu.keyboardRow(testButtons);
        menu.keyboardRow(
                List.of(
                        InlineKeyboardButton.builder()
                                .text("Back to group 👥")
                                .callbackData("/groupinfo").build()));
        return new Pair(menu.build(), strB.toString());
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

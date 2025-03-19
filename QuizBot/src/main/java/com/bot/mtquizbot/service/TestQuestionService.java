package com.bot.mtquizbot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.bot.mtquizbot.models.QuestionType;
import com.bot.mtquizbot.models.QuestionTypeEnum;
import com.bot.mtquizbot.models.TestQuestion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestQuestionService extends BaseService {

    public List<TestQuestion> getQuestionsByTestId(String apiToken, String testId, int limit, int offset) {
        throw new UnsupportedOperationException();
    }

    public TestQuestion getQuestionById(String apiToken, String testId, String questionId) {
        throw new UnsupportedOperationException();
    }

    public String addQuestion(String apiToken, String testId, QuestionTypeEnum type, String text, int weight) {
        throw new UnsupportedOperationException();
    }

    public void updateTestQuestion(String apiToken, String testId, String questionId, String text, QuestionTypeEnum type, int weight) {
        throw new UnsupportedOperationException();
    }

    public List<String> getAnswers(String apiToken, String questionId, Boolean getFalse) {
        throw new UnsupportedOperationException();
    }

    public void addAnswers(String apiToken, String questionId, Boolean getFalse, List<String> answers) {
        throw new UnsupportedOperationException();
    }

    public void replaceAllAnswers(String apiToken, String questionId, Boolean getFalse, List<String> answers) {
        throw new UnsupportedOperationException();
    }

    public InlineKeyboardMarkupBuilder getQuestionsMenuBuilder(List<TestQuestion> questions, int buttonsInSingleRow) {
        var menu = InlineKeyboardMarkup.builder();
        List<InlineKeyboardButton> row = new ArrayList<>();
        int cnt = 0;
        for (var question : questions) {
            cnt++;
            row.add(InlineKeyboardButton.builder()
                    .text(Integer.toString(cnt) + " ✅")
                    .callbackData("/editquestion " + question.getId())
                    .build());
            if (row.size() == buttonsInSingleRow) {
                menu.keyboardRow(row);
                row = new ArrayList<>();
            }
        }
        if (!row.isEmpty())
            menu.keyboardRow(row);
        return menu;
    }

    public InlineKeyboardMarkupBuilder getQuestionTypeMenuBuilder(List<QuestionType> types, int typeButtonsInARow) {
        var menu = InlineKeyboardMarkup.builder();
        List<InlineKeyboardButton> list = new ArrayList<>();
        for (var type : types) {
            list.add(InlineKeyboardButton.builder()
                    .text(type.getHumanReadableName())
                    .callbackData("/addquestionstagetype " + type.getType().name())
                    .build());
            if (list.size() == typeButtonsInARow) {
                menu.keyboardRow(list);
                list = new ArrayList<>();
            }
        }
        if (!list.isEmpty())
            menu.keyboardRow(list);
        return menu;
    }

    public List<QuestionType> getQuestionTypeList() {
        throw new UnsupportedOperationException();
    }

    public InlineKeyboardMarkupBuilder getQuestionEditMenu(TestQuestion question) {
        var menu = BaseService.getEditMenuBuilder(question, "/setqfield");
        if (question.getType() == QuestionTypeEnum.ChooseSingle || 
            question.getType() == QuestionTypeEnum.ChooseMultiple)
            menu.keyboardRow(
                    List.of(
                            InlineKeyboardButton.builder()
                                    .text("Check false answers 🙈")
                                    .callbackData("/falseanswers " + question.getId()).build()));
        menu.keyboardRow(
                List.of(
                        InlineKeyboardButton.builder()
                                .text("Back to questions 📍")
                                .callbackData("/editquestions " + question.getTestId())
                                .build()));
        return menu;
    }

    public InlineKeyboardMarkupBuilder getFalseAnswersMenu(String questionId) {
        return InlineKeyboardMarkup.builder().keyboardRow(
                List.of(
                        InlineKeyboardButton.builder()
                                .text("Add false answer ⭕️")
                                .callbackData("/addfalseanswer " + questionId)
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Back to question ❓")
                                .callbackData("/editquestion " + questionId)
                                .build()));
    }

    public String getQuestionDescriptionMessage(TestQuestion question) {
        return question.getText() +
                "\nAnswer: " + (question.getAnswer() == null ? "No answer, please add one" : question.getAnswer()) +
                "\nWeight: " + Integer.toString(question.getWeight());
    }

    public String getQuestionTypeDescriptionMessage(List<QuestionType> types) {
        var strB = new StringBuilder();
        for (var type : types) {
            strB.append(type.getHumanReadableName() + " - " + type.getDescription() + "\n");
        }
        return strB.toString();
    }

    public String getQuestionDescriptionMessage(List<TestQuestion> questions) {
        var strB = new StringBuilder();
        int cnt = 0;
        for (var question : questions) {
            cnt++;
            strB.append(Integer.toString(cnt) + "): " + question.getText() +
                    "\nWeight - " + Integer.toString(question.getWeight()) +
                    "\n");
        }
        return strB.toString();
    }

    public InlineKeyboardMarkupBuilder getChooseQuestionMenu(TestQuestion question) {
        throw new UnsupportedOperationException();
    }
}
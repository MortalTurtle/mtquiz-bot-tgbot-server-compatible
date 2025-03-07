package com.bot.mtquizbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bot.mtquizbot.models.BotState;
import com.bot.mtquizbot.models.TestQuestion;
import com.bot.mtquizbot.models.User;
import com.bot.mtquizbot.repository.IRedisRepository;
import com.bot.mtquizbot.tgbot.IntermediateVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService extends BaseService {
    protected final IRedisRepository cache;

    public List<User> getUserList() {
        throw new UnsupportedOperationException();
    }

    public User getById(long id) {
        throw new UnsupportedOperationException();
    }

    public User getById(String id) {
        throw new UnsupportedOperationException();
    }

    public void insert(User entity) {
        throw new UnsupportedOperationException();
    }

    public void updateGroupById(long id, String groupId) {
        throw new UnsupportedOperationException();
    }

    public void updateGroupById(String id, String groupId) {
        throw new UnsupportedOperationException();
    }

    public BotState getBotState(String userId) {
        log.trace("#### getBotState() [userId={}]", userId);
        return cache.getBotStateByUser(userId);
    }

    public String getIntermediateVarString(String userId, IntermediateVariable var) {
        log.trace("#### getIntermediateVarString() [user_id={}, var={}]", userId, var);
        return cache.getIntermediateVar(userId, var);
    }

    public void putIntermediateVar(String userId, IntermediateVariable var, String value) {
        log.trace("#### putIntermediateVar() [user_id={}, var={}, value={}]", userId, var, value);
        cache.putIntermediateVar(userId, var, value);
    }

    public void putBotState(String userId, BotState state) {
        log.trace("#### putBotState() [userId={}, state={}]", userId, state.name());
        cache.putBotState(userId, state);
    }

    public void putQuestionsId(String userId, List<TestQuestion> questions) {
        log.trace("#### putQuestionsId() [user_id={}, questions={}]", userId, questions);
        cache.putQuestionsId(userId, questions);
    }

    public String getQuestionId(String userId, Integer index) {
        log.trace("#### getQuestionId() [user_id={}, index={}]", userId, index);
        return cache.getQuestionId(userId, index);
    }

    public Integer getUserScore(String userId, String testId) {
        log.trace("#### getUserScore() [user_id={}, testId={}]", userId, testId);
        return cache.getUserScore(userId, testId);
    }

    public void putUserScore(String userId, String testId, Integer score) {
        log.trace("#### putUserScore() [user_id={}, testId={}, score={}]", userId, testId, score);
        cache.putUserScore(userId, testId, score);
    }

    public void putCurrentQuestionNum(String userId, Integer num) {
        log.trace("#### putCurrentQuestionNum() [user_id={}, num={}]", userId, num);
        cache.putCurrentQuestionNum(userId, num);
    }

    public Integer getCurrentQuestionNum(String userId) {
        log.trace("#### getCurrentQuestionNum() [user_id={}]", userId);
        return cache.getCurrentQuestionNum(userId);
    }

}
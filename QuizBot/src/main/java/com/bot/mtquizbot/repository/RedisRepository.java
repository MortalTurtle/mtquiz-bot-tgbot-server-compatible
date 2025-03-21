package com.bot.mtquizbot.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bot.mtquizbot.models.BotState;
import com.bot.mtquizbot.models.TestQuestion;
import com.bot.mtquizbot.tgbot.IntermediateVariable;

import jakarta.annotation.PostConstruct;

@Repository
public class RedisRepository implements IRedisRepository {
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, String> hashOperations;
    private static Map<String, BotState> stateByName;
    private static final String BOT_STATE_KEY = "bot_state";
    private static final String CURR_Q_NUM_KEY = "question_num";
    private static final String API_TOKEN_KEY = "api_token_key";
    private static final String INTERNAL_ID_KEY = "internal_id_key";
    private static final String TELEGRAM_ID_KEY = "telegram_id_key";

    @Autowired
    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
        if (stateByName == null) {
            stateByName = new HashMap<>();
            var states = BotState.values();
            for (var state : states)
                stateByName.put(state.name(), state);
        }
    }

    @Override
    public void putIntermediateVar(String userId, IntermediateVariable varKey, String value) {
        hashOperations.put(userId, varKey.name(), value);
    }

    @Override
    public void putQuestionsId(String userId, List<TestQuestion> questions) {
        for (int i = 0; i < questions.size(); i++) {
            hashOperations.put(userId, Integer.toString(i), questions.get(i).getId());
        }
    }

    @Override
    public void putCurrentQuestionNum(String userId, Integer num) {
        hashOperations.put(userId, CURR_Q_NUM_KEY, num.toString());
    }

    @Override
    public Integer getCurrentQuestionNum(String userId) {
        return Integer.valueOf(hashOperations.get(userId, CURR_Q_NUM_KEY));
    }

    @Override
    public String getQuestionId(String userId, Integer index) {
        return hashOperations.get(userId, Integer.toString(index));
    }

    @Override
    public Integer getUserScore(String userId, String testId) {
        return Integer.valueOf(hashOperations.get(userId, testId));
    }

    @Override
    public void putUserScore(String userId, String testId, Integer score) {
        hashOperations.put(userId, testId, Integer.toString(score));
    }

    @Override
    public String getIntermediateVar(String userId, IntermediateVariable varKey) {
        return (String) hashOperations.get(userId, varKey.name());
    }

    @Override
    public BotState getBotStateByUser(String userId) {
        return stateByName.get((String) hashOperations.get(userId, BOT_STATE_KEY));
    }

    @Override
    public void putBotState(String userId, BotState state) {
        hashOperations.put(userId, BOT_STATE_KEY, state.name());
    }

    @Override
    public void putApiToken(String userId, String token) {
        hashOperations.put(userId, API_TOKEN_KEY, token);
    }

    @Override
    public String getApiToken(String userId) {
        return hashOperations.get(userId, API_TOKEN_KEY);
    }

    @Override
    public String getUserInternalId(Long userId) {
        return hashOperations.get(Long.toString(userId), INTERNAL_ID_KEY);
    }

    @Override
    public void putUserInternalId(Long userId, String internalId) {
        hashOperations.put(Long.toString(userId), INTERNAL_ID_KEY, internalId);
    }

    @Override
    public Long getUserTelegramId(String internalId) {
        return Long.parseLong(hashOperations.get(internalId, TELEGRAM_ID_KEY));
    }

    @Override
    public void putUserTelegramId(Long userId, String internalId) {
        hashOperations.put(internalId, TELEGRAM_ID_KEY, userId.toString());
    }
}
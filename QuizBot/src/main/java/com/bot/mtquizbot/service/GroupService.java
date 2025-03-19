package com.bot.mtquizbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.mtquizbot.models.TestGroup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GroupService extends BaseService {

    public TestGroup getById(String apiToken, String id) {
        throw new UnsupportedOperationException();
    }

    public String create(String apiToken, String name, String description) {
        throw new UnsupportedOperationException();
    }
}
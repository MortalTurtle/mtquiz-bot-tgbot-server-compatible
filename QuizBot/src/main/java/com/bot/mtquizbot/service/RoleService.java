package com.bot.mtquizbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bot.mtquizbot.models.GroupRole;
import com.bot.mtquizbot.models.RoleDb;
import com.bot.mtquizbot.models.TestGroup;
import com.bot.mtquizbot.models.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService extends BaseService {

    public GroupRole getById(String id) {
        throw new UnsupportedOperationException();
    }

    public List<RoleDb> getRoleDbList() {
        throw new UnsupportedOperationException();
    }

    public GroupRole getUserRole(User user, TestGroup group) {
        throw new UnsupportedOperationException();
    }

    public void addUserRole(TestGroup group, User user, GroupRole role) {
        throw new UnsupportedOperationException();
    }
}
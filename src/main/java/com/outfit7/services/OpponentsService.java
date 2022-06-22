package com.outfit7.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.outfit7.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class OpponentsService {

    @Inject
    ClassicMatchingService classicMatchingService;

    public List<User> matchOpponents(String userId) {
        log.info("Running classic matcher for userId: '{}'", userId);
        return classicMatchingService.retrieveOpponents(userId);
    }

}

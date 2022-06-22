package com.outfit7.services;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.outfit7.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ClassicMatchingService {

    @Inject
    UserService userService;

    public List<User> retrieveOpponents(String userId) {
        User currentUser = userService.get(userId);
        log.debug("Found user: '{}'", currentUser);
        return matchOpponents(currentUser);
    }

    private List<User> matchOpponents(User currentUser) {
        return userService.getAll().stream()
                .filter(opponent -> !opponent.getId().equals(currentUser.getId()))
                .filter(distinctByKey(User::getId))
                .filter(distinctByName(User::getPlayerName))
                .filter(filterByPowerLevel(currentUser))
                .collect(Collectors.toList());
    }

    private static Predicate<User> filterByPowerLevel(User currentUser) {
        return opponent ->
                opponent.getPowerLevel() <= currentUser.getPowerLevel() + 15
                        && opponent.getPowerLevel() >= currentUser.getPowerLevel() - 15;
    }

    private static Predicate<User> distinctByKey(Function<User, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(t.getId());
    }

    private static Predicate<User> distinctByName(Function<User, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(t.getPlayerName());
    }

}

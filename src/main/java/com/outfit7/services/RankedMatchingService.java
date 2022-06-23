package com.outfit7.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.outfit7.entity.User;

import com.outfit7.entity.exception.NotEnoughUsersFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class RankedMatchingService {

    @Inject
    UserService userService;

    public List<User> retrieveOpponents(String userId) {
        userService = new UserService();
        User currentUser = userService.get(userId);
        log.debug("Found user: '{}'", currentUser);
        return matchOpponents5(currentUser, userService);
    }

    private List<User> matchOpponents5(User currentUser, UserService userService) {
        // get all opponents by rank
        List<User> opponents =  userService.getAll().stream()
                .filter(opponent -> !opponent.getId().equals(currentUser.getId()))
                .filter(distinctByKey(User::getId))
                .filter(distinctByName(User::getPlayerName))
                .filter(filterByRank(currentUser))
                .collect(Collectors.toList());

        // choose 5 at random if possible
        if (opponents.size() < 5)
            throw new NotEnoughUsersFoundException("Not enough players found in your rank");

        return randomFive(opponents);
    }

    private static List<User> randomFive(List<User> oponents) {
        Random random = new Random();
        List<User> chosenOpponents = new ArrayList<>();
        while(chosenOpponents.size() != 5) {
            int chosen = random.nextInt(oponents.size());
            chosenOpponents.add(oponents.get(chosen));
            oponents.remove(chosen);
        }
        return chosenOpponents;
    }

    private static Predicate<User> filterByRank(User currentUser) {
        return opponent ->
                opponent.getRank() <= currentUser.getRank() + 100
                        && opponent.getRank() >= currentUser.getRank() - 100;
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
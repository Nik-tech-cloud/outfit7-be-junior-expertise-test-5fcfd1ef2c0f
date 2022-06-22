package com.outfit7.utils;

import java.util.ArrayList;
import java.util.List;

import com.outfit7.entity.User;
import com.outfit7.entity.User.Champion;
import com.outfit7.entity.User.Hero;

public class TestUtils {

    public static User user() {
        return users().get(0);
    }

    public static List<User> users() {
        return List.of(
                User.builder().playerName("name1").powerLevel(10L).id("1").hero(hero(1L)).champions(champions()).build(),
                User.builder().playerName("name1").powerLevel(11L).id("2").hero(hero(2L)).champions(champions()).build(),
                User.builder().playerName("name2").powerLevel(7L).id("3").hero(hero(3L)).champions(champions()).build(),
                User.builder().playerName("name2").powerLevel(4L).id("3").hero(hero(3L)).champions(champions()).build(),
                User.builder().playerName("name3").powerLevel(40L).id("4").hero(hero(3L)).champions(champions()).build(),
                User.builder().playerName("name3").powerLevel(35L).id("4").hero(hero(3L)).champions(champions()).build(),
                User.builder().playerName("name4").powerLevel(8L).id("5").hero(hero(3L)).champions(champions()).build(),
                User.builder().playerName("name4").powerLevel(9L).id("6").hero(hero(4L)).champions(champions()).build(),
                User.builder().playerName("name5").powerLevel(30L).id("7").hero(hero(4L)).champions(champions()).build(),
                User.builder().playerName("name5").powerLevel(13L).id("7").hero(hero(5L)).champions(champions()).build());
    }

    public static Hero hero(Long level) {
        return Hero.builder()
                .id("HeroId" + level)
                .name("HeroName" + level)
                .level(level)
                .build();
    }

    public static List<Champion> champions() {
        List<Champion> champions = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            champions.add(Champion.builder()
                    .id("ChampionId" + i)
                    .name("ChampionName" + i)
                    .level((long) i)
                    .build());
        }
        return champions;
    }

}

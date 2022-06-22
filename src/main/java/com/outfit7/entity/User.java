package com.outfit7.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {

    String id;

    String playerName;

    //added this for ranked mode
    Long rank;

    Long powerLevel;

    Hero hero;

    List<Champion> champions;

    @Value
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class Champion {

        String id;

        Long level;

        String name;

    }

    @Value
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    public static class Hero {

        String id;

        Long level;

        String name;

    }

}

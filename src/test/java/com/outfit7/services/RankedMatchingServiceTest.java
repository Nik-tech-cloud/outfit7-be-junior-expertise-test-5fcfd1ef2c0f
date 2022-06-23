package com.outfit7.services;

import static com.outfit7.utils.TestUtils.user;
import static com.outfit7.utils.TestUtils.users;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.outfit7.entity.User;

@ExtendWith(MockitoExtension.class)
class RankedMatchingServiceTest {

    @Mock
    UserService userService;

    @InjectMocks
    OpponentsService opponentServ;

    @Test
    void shouldRetrieveOpponentsForUserIdRanked() {
        // Given
        String uId = "d7fc5c61-ac15-48ca-9b14-f3d8f55b1946";

        // When
        List<User> opponents = opponentServ.matchOpponentsRanked(uId);

        // Then
        // Changed a few values (removed no. 6) - second assignment
        assertThat(opponents)
                .hasSize(5);
    }


}
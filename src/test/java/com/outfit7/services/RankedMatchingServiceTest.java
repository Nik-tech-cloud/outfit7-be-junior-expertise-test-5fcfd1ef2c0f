package com.outfit7.services;

import static com.outfit7.utils.TestUtils.user;
import static com.outfit7.utils.TestUtils.users;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.outfit7.entity.exception.EntityNotFoundException;
import com.outfit7.entity.exception.NotEnoughUsersFoundException;
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
    RankedMatchingService classicMatchingService;

    @Test
    void shouldRetrieveFiveDifferentOpponents() {
        // Given
        String userId = "d7fc5c61-ac15-48ca-9b14-f3d8f55b1946";

        // When
        List<User> opponents = classicMatchingService.retrieveOpponents(userId);
        List<User> opponentsTwo = classicMatchingService.retrieveOpponents(userId);

        // Then
        assertThat(opponents).hasSize(5);
        assertThat(opponentsTwo).isNotEqualTo(opponents);
    }
    @Test
    void shouldThrowException() {
        // Given
        String userId = "ad8f2c58-1d9b-41c6-b540-179e1d5bbe60";


        // When
        Throwable thrown = catchThrowable(() -> classicMatchingService.retrieveOpponents(userId));

        // Then
        assertThat(thrown)
                .isInstanceOf(NotEnoughUsersFoundException.class);
    }
}
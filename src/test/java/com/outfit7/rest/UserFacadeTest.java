package com.outfit7.rest;

import static com.outfit7.utils.TestUtils.champions;
import static com.outfit7.utils.TestUtils.hero;
import static com.outfit7.utils.TestUtils.users;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;

import com.outfit7.entity.User;
import com.outfit7.entity.exception.EntityNotFoundException;
import com.outfit7.json.JsonUtils;
import com.outfit7.services.UserService;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@QuarkusTest
class UserFacadeTest {

    @InjectMock
    UserService userService;

    @Test
    public void shouldReturnAllUsers() throws JAXBException {
        // Given
        List<User> users = users();

        given(userService.getAll())
                .willReturn(users);

        // When
        Response response = RestAssured.given()
                .when()
                .get("/user");

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK.getStatusCode());

        List<User> returnedUsers = JsonUtils.deserializeToList(response.getBody().asString(), User.class);
        assertThat(returnedUsers).isEqualTo(users);
    }

    @Test
    public void shouldReturnUserForSelectedUserId() throws JAXBException {
        // Given
        String userId = "some-user-id";
        User user = User.builder().id("1").hero(hero(1L)).champions(champions()).build();

        given(userService.get(eq(userId)))
                .willReturn(user);

        // When
        Response response = RestAssured.given()
                .when()
                .get("/user/{userId}", userId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK.getStatusCode());

        User returnedUser = JsonUtils.deserialize(response.getBody().asString(), User.class);
        assertThat(returnedUser).isEqualTo(user);
    }

    @Test
    public void shouldThrowExceptionWhenUserDoesNotExist() {
        // Given
        String userId = "some-user-id";

        given(userService.get(eq(userId)))
                .willThrow(new EntityNotFoundException("Not found!"));

        // When
        Response response = RestAssured.given()
                .when()
                .get("/matching/classic/{userId}", userId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND.getStatusCode());
    }

}
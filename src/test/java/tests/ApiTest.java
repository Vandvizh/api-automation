package tests;

import clients.UserClient;
import models.User;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.restassured.response.Response;
import java.util.List;
import static specifications.ResponseSpecs.OK_RESPONSE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.BeforeEach;




public class ApiTest {
    private UserClient userClient;
    @BeforeEach
    void setUp() {
        userClient = new UserClient();
    }


    @Test
    void getUsersTest() {

        Response response = userClient.getUsers();

        response.then()
                .spec(OK_RESPONSE);

        List<User> users = response.jsonPath()
                .getList("", User.class);

        assertEquals(10, users.size());
    }


    @Test
    void getUserByIdTest() {

        Response response = userClient.getUserById(2);

        response.then()
                .spec(OK_RESPONSE);

        User user = response.as(User.class);

        assertEquals(2, user.getId());
        assertEquals("Ervin Howell", user.getName());
        assertEquals("Shanna@melissa.tv", user.getEmail());
    }

    @ParameterizedTest
    @CsvSource({
            "999",
            "1000",
            "-1"
    })
    void getNonExistingUserTest(int userId) {

        Response response = userClient.getUserById(userId);

        response.then()
                .statusCode(404);
    }

    @Test
    void createUserTest() {
        User user = new User(
                "John Doe",
                "johndoe",
                "john@example.com"
        );


        Response response = userClient.createUser(user);
        response.then()
                .statusCode(201);

        User createdUser = response.as(User.class);

        assertEquals("John Doe", createdUser.getName());
        assertTrue(createdUser.getId() > 0);
        assertEquals("johndoe", createdUser.getUsername());
        assertEquals("john@example.com", createdUser.getEmail());
    }

    @Test
    void deleteUserTest() {

        Response response = userClient.deleteUser(2);

        response.then()
                .spec(OK_RESPONSE);
    }

    @Test
    void updateUserTest() {

        User user = new User(
                "Updated Name",
                "updateduser",
                "updated@example.com"
        );

        Response response = userClient.updateUser(2, user);

        response.then()
                .spec(OK_RESPONSE);

        User updatedUser = response.as(User.class);

        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }
}
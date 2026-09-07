package tests;

import clients.UserClient;
import models.User;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.hasItem;
import io.restassured.response.Response;
import java.util.List;



public class ApiTest {

    @Test
    void getUserAndUseIdTest() {
        Response response = given()
                .get("https://jsonplaceholder.typicode.com/users/2");

        int userId = response.jsonPath().getInt("id");

        Response secondResponse = given()
                .pathParam("id", userId)
                .get("https://jsonplaceholder.typicode.com/users/{id}");

        secondResponse.then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    @Test
    void createAndGetUserTest() {
        UserClient userClient = new UserClient();

        User user = new User(
                "John Doe",
                "johndoe",
                "john@example.com"
        );

        User createdUser = userClient.createUser(user);

        assertTrue(createdUser.getId() > 0);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("johndoe", createdUser.getUsername());
        assertEquals("john@example.com", createdUser.getEmail());
    }

    @Test
    void getUsersTest() {
        UserClient userClient = new UserClient();

        List<User> users = userClient.getUsers();

        assertEquals(10, users.size());
        assertEquals(5, users.get(4).getId());
    }


    @Test
    void getUserByIdTest() {
        UserClient userClient = new UserClient();

        Response response = userClient.getUserById(2);

        response.then()
                .statusCode(200);

        User user = response.as(User.class);

        assertEquals(2, user.getId());
        assertEquals("Ervin Howell", user.getName());
        assertEquals("wrong@ex.com", user.getEmail());
    }

    @Test
    void getNonExistingUserTest() {
        UserClient userClient = new UserClient();

        Response response = userClient.getUserById(999);

        response.then()
                .statusCode(404);
    }

    @Test
    void createUserTest() {
        User user = new User("John Doe", "johndoe", "john@example.com");

        UserClient userClient = new UserClient();
        User createdUser = userClient.createUser(user);
        assertEquals("John Doe", createdUser.getName());
        assertTrue(createdUser.getId() > 0);
        assertEquals("johndoe", createdUser.getUsername());
    }
    @Test
    void deleteUserTest() {
        UserClient userClient = new UserClient();

        // Отправляем DELETE-запрос для пользователя с id = 2.
        // clients.UserClient внутри проверяет, что API вернул статус 200.
        userClient.deleteUser(2);
    }
    @Test
    void updateUserTest() {
        UserClient userClient = new UserClient();

        User user = new User("Updated Name", "updateduser", "updated@example.com");

        User updatedUser = userClient.updateUser(2, user);

        // Проверяем, что API вернул те данные, которые мы отправили.
        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }
}
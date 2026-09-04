package clients;
import config.Config;
import models.User;
import java.util.List;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;



public class UserClient {

    public List<User> getUsers() {
        return given()
                .get(Config.BASE_URL + USERS_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", User.class);
    }

    private static final String USERS_ENDPOINT = "/users";

    public User createUser(User user) {
        return given()
                .body(user)
                .header("Accept", "application/json")
                .contentType("application/json")
                .post(Config.BASE_URL + USERS_ENDPOINT)
                .then()
                .statusCode(201)
                .extract()
                .as(User.class);
    }

    public Response getUserById(int userId) {
        return given()
                .pathParam("id", userId)
                .get(Config.BASE_URL + USERS_ENDPOINT + "/{id}");
    }

    public User updateUser(int userId, User user) {
        return given()
                .pathParam("id", userId)
                .header("Accept", "application/json")
                .body(user)
                .contentType("application/json")
                .put(Config.BASE_URL + USERS_ENDPOINT + "/{id}")
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);
    }

    public void deleteUser(int userId) {
        given()
                .pathParam("id", userId)
                .delete(Config.BASE_URL + USERS_ENDPOINT + "/{id}")
                .then()
                .statusCode(200);

    }
}
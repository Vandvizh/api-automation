package clients;
import config.Config;
import models.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;



public class UserClient {

    static {
        RestAssured.baseURI = Config.BASE_URL;
    }

    private static final String USERS_ENDPOINT = "/users";
    private static final RequestSpecification JSON_REQUEST_SPEC =
            new RequestSpecBuilder()
                    .setContentType("application/json")
                    .setAccept("application/json")
                    .build();

    public Response getUsers() {
        return given()
                .get(USERS_ENDPOINT);
    }

    public Response createUser(User user) {
        return given()
                .spec(JSON_REQUEST_SPEC)
                .body(user)
                .post(USERS_ENDPOINT);
    }

    public Response getUserById(int userId) {
        return given()
                .pathParam("id", userId)
                .get(USERS_ENDPOINT + "/{id}");
    }

    public Response updateUser(int userId, User user) {
        return given()
                .spec(JSON_REQUEST_SPEC)
                .pathParam("id", userId)
                .body(user)
                .put(USERS_ENDPOINT + "/{id}");
    }

    public Response deleteUser(int userId) {
        return given()
                .pathParam("id", userId)
                .delete(USERS_ENDPOINT + "/{id}");
    }

}
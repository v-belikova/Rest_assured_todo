import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateTodoTest {
    Routes routes = new Routes();
    Methods methods = new Methods();

    @Epic("POST /v1/todo")
    @Feature("Positive test")
    @Test
    public void createTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL),Specifications.responseSpecOk200());
        CreateTodo createTodo = new CreateTodo("Hello");
        Response response = given()
                .body(createTodo)
                .when()
                .post("/v1/todo")
                .then().log().all()
                .body("data.id", notNullValue())
                .body("data.createdAt", notNullValue())
                .body("data.updatedAt", notNullValue())
                .extract().response();

        String text = response.jsonPath().getString("data.text");

        Assert.assertEquals("Hello", text);
    }
    @Epic("POST /v1/todo")
    @Feature("Negative test")
    @Test
    public void createNewsNegativeTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL),Specifications.responseSpec400());
        CreateTodo createTodo = new CreateTodo(methods.generateRandomHexString(1000));
        Response response = given()
                .body(createTodo)
                .when()
                .post("/v1/todo")
                .then().log().all()
                .extract().response();
        int statusCode = response.jsonPath().getInt("statusCode");
        String success = response.jsonPath().getString("success");

        Assert.assertEquals(statusCode, 32);
        Assert.assertEquals(success, "true");

    }
}


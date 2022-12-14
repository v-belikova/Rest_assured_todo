import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

public class GetPaginatedTodoTest2 {
    SoftAssertions softAssertions = new SoftAssertions();
    ErrorCode errorCode = new ErrorCode();
    Routes routes = new Routes();
    JSONObject requestBody = new JSONObject();
    RequestSpecification request = RestAssured.given();


    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильный вывод всех задач")
    @Description(value = "Вывод всех выполненных задач ")
    @Test
    public void getPaginatedTodoTest1(){
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Map<String, Object> paginationTodo = request.queryParam("page", "1"  )
                .and().queryParam("perPage", "90")
                .and().queryParam("status", "true")
                .get(routes.getPaginatedTodo).then().log().all().assertThat().statusCode(200)
                .extract().body().as(new TypeRef<Map<String, Object>>() {});

        String statusCode =paginationTodo.get("statusCode").toString();
        String success = paginationTodo.get("success").toString();

        Assertions.assertNotNull(paginationTodo);
        //softAssertions.assertThat("1").isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильный вывод всех задач")
    @Description(value = "Вывод всех невыполненных задач ")
    @Test
    public void getPaginatedTodoTest2(){
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Map<String, Object> paginationTodo = request.queryParam("page", "1"  )
                .and().queryParam("perPage", "90")
                .and().queryParam("status", "false")
                .get(routes.getPaginatedTodo).then().log().all().assertThat().statusCode(200)
                .extract().body().as(new TypeRef<Map<String, Object>>() {});

        String statusCode =paginationTodo.get("statusCode").toString();
        String success = paginationTodo.get("success").toString();

        Assertions.assertNotNull(paginationTodo);
        softAssertions.assertThat("1").isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

}
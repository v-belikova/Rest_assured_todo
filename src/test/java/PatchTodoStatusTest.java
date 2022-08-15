import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchTodoStatusTest {

    Routes routes = new Routes();
    JSONObject requestBody = new JSONObject();
    Boolean status = true;

    @Epic("PATCH /v1/todo/status/{id}")
    @Description("Positive test")
    @Test
    public  void patchTodoTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        CreateTodo createTodo = new CreateTodo("Приветики пистолетики");
        Response response = given()
                .body(createTodo)
                .when()
                .post("/v1/todo")
                .then().log().all()
                .extract().response();
        int id = response.jsonPath().getInt("data.id");

        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        requestBody.put("status", status);
        Response response1 = given()
                .body(requestBody.toString())
                .when()
                .patch("/v1/todo/status/" + id)
                .then().log().all()
                .extract().response();

        int statusCode = response1.jsonPath().getInt("statusCode");
        String success = response1.jsonPath().getString("success");

        Assert.assertEquals(statusCode,1);
        Assert.assertEquals(success,"true");
    }
}



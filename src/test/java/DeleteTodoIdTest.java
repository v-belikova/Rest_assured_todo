import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteTodoIdTest {
    Routes routes = new Routes();

    @Epic("DELETE /v1/todo/{id}")
    @Description("Positive test")
    @Test
    public  void deleteTodoIdTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        CreateTodo createTodo = new CreateTodo("Приветики пистолетики");
        Response response = given()
                .body(createTodo)
                .when()
                .post("/v1/todo")
                .then().log().all()
                .extract().response();
        int id = response.jsonPath().getInt("data.id");
        //System.out.println(id);
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        Response response1 = given()
                .when()
                .delete("/v1/todo/" + id)
                .then().log().all()
                .extract().response();
        int statusCode = response1.jsonPath().getInt("statusCode");
        String success = response1.jsonPath().getString("success");

        Assert.assertEquals(statusCode, 1);
        Assert.assertEquals(success, "true");
    }
    @Epic("DELETE /v1/todo/{id}")
    @Description("Negative test")
    @Test
    public  void deleteTodoIdNegativeTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        CreateTodo createTodo = new CreateTodo("Приветики пистолетики");
        Response response = given()
                .body(createTodo)
                .when()
                .post("/v1/todo")
                .then().log().all()
                .extract().response();
        int id = response.jsonPath().getInt("data.id");
        //System.out.println(id);
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpec400());
        Response response1 = given()
                .when()
                .delete("/v1/todo/"+ 123456789)
                .then().log().all()
                .extract().response();
        int statusCode = response1.jsonPath().getInt("statusCode");
        String success = response1.jsonPath().getString("success");

        Assert.assertEquals(statusCode, 34);
        Assert.assertEquals(success, "true");
    }

}

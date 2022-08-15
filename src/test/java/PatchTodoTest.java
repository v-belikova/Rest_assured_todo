import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchTodoTest {

    Routes routes = new Routes();
    JSONObject requestBody = new JSONObject();
    Boolean status = true;

    @Epic("PATCH /v1/todo")
    @Description("Positive test")
    @Test
    public  void patchTodoTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        requestBody.put("status", status);
        Response response = given()
                .body(requestBody.toString())
                .when()
                .patch("/v1/todo")
                .then().log().all()
                .extract().response();

        int statusCode = response.jsonPath().getInt("statusCode");
        String success = response.jsonPath().getString("success");

        Assert.assertEquals(statusCode,1);
        Assert.assertEquals(success,"true");
    }
}

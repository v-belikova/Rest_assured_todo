import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAllReadyTodoTest {
    Routes routes = new Routes();

    @Epic("DELETE deleteAllReady")
    @Description("Positive test")
    @Test
    public void deleteAllUserTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL), Specifications.responseSpecOk200());
        Response response = given()
                .when()
                .delete("/v1/todo")
                .then().log().all()
                .extract().response();

        int statusCode = response.jsonPath().getInt("statusCode");
        String success = response.jsonPath().getString("success");

        Assert.assertEquals(statusCode, 1);
        Assert.assertEquals(success, "true");

    }
}


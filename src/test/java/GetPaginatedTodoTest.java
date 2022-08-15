import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetPaginatedTodoTest {
    Routes routes = new Routes();
    @Epic("GET /v1/todo")
    @Description ("Positive test")
    @Test
    public void getPaginatedTest(){
        Specifications.insallSpecification(Specifications.requestSpec(routes.URL),Specifications.responseSpecOk200());
        int statusCode = 0;

        GetPaginationResponse getPaginationResponse = new GetPaginationResponse();
        List<GetPaginationResponse> todo = given()
                .queryParam("page","1")
                .queryParam("perPage", "100")
                .queryParam("status", "true")
                .when()
                .get("/v1/todo")
                .then().log().all()
                .body("data.content.id", notNullValue())
                .extract().body().jsonPath().getList("data.content", GetPaginationResponse.class);

        Assert.assertEquals(getPaginationResponse.getStatusCode(), statusCode);


    }
}

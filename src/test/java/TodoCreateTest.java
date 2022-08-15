import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;
public class TodoCreateTest {
    SoftAssertions softAssertions = new SoftAssertions();
    Methods methods = new Methods();
    Routes routes = new Routes();
    ErrorCode errorCode = new ErrorCode();
    JSONObject requestBody = new JSONObject();
    RequestSpecification request = RestAssured.given();

    String text = Methods.generateRandomHexString(100);
    String superLongText = Methods.generateRandomHexString(1000);

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильное создание задачи")
    @Description(value = "Передаю в тело запроса текст на 100 символов")
    @Test
    public void todoCreateTest(){
        methods.loginDefault();
        requestBody.put("text", text);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.log().all()
                .post(routes.todoCreate).then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        softAssertions.assertThat(200).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Неправильное создание задачи")
    @Description(value = "Передаю в тело запроса текст на 1000 символов и вылетает ошибка")
    @Test
    public void negativeTodoCreateTest(){
        methods.loginDefault();
        requestBody.put("text", superLongText);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.log().all()
                .post(routes.todoCreate).then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");

        softAssertions.assertThat(400).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertThat(errorCode.TODO_TEXT_SIZE_NOT_VALID).isEqualTo(customStatusCode);
        softAssertions.assertAll();
    }
}
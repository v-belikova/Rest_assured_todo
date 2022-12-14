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
public class TodoDeleteTest {
    SoftAssertions softAssertions = new SoftAssertions();
    Methods1 methods1 = new Methods1();
    Routes routes = new Routes();
    ErrorCode errorCode = new ErrorCode();
    JSONObject requestBody = new JSONObject();
    RequestSpecification request = RestAssured.given();

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильное удаление задачи по Id")
    @Description(value = "Вытягиваю id задачи и передаю его в маршрут запроса")
    @Test
    public void deleteTodoTest(){
        methods1.loginDefault();
        Response response = methods1.todoCreate();
        int id = response.jsonPath().getInt("data.id");
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());


        response = request.log().all().delete(routes.deleteTodo + id).then().log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        softAssertions.assertThat(200).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильное удаление всех выполненных задач")
    @Description(value = "Удаление через правильныый маршрут")
    @Test
    public void deleteTodoAllTest(){
        Response response = methods1.loginDefault();
        String token = response.jsonPath().getMap("data").get("token").toString().substring(7);
        methods1.todoCreate();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        response = request.auth().oauth2(token).log().all().delete(routes.getPaginatedTodo).then().log().all().contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        softAssertions.assertThat(200).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Неправильное удаление задачи по Id")
    @Description(value = "Передаю в маршрут запроса несуществующий Id")
    @Test
    public void negativeDeleteTodoTest(){
        methods1.loginDefault();
        methods1.todoCreate();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());


        Response response = request.log().all().delete(routes.deleteTodo + "id").then().log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");

        softAssertions.assertThat(400).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertThat(errorCode.MAX_UPLOAD_SIZE_EXCEEDED).isEqualTo(customStatusCode);
        softAssertions.assertAll();
    }

}

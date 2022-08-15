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

public class PatchTodoTest {
    SoftAssertions softAssertions = new SoftAssertions();
    Methods methods = new Methods();
    Routes routes = new Routes();
    ErrorCode errorCode = new ErrorCode();
    JSONObject requestBody = new JSONObject();
    RequestSpecification request = RestAssured.given();

    boolean status = true;

    String text ="Проверка обновления текста";

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильное обновление статуса задач по Id")
    @Description(value = "Помечаю статус задачи как выполненный ")
    @Test
    public void patchTodoTestForId(){
        methods.loginDefault();
        Response response = methods.todoCreate();
        int id = response.jsonPath().getInt("data.id");
        requestBody.put("status", status);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        response = request.log().all().patch(routes.patchTodo + id)
                .then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        softAssertions.assertThat(200).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильное обновление статуса всех задач")
    @Description(value = "Помечаю статус всех задач как выполненный ")
    @Test
    public void patchTodoTestAll(){
        methods.loginDefault();
        methods.todoCreate();
        requestBody.put("status", status);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.log().all().patch(routes.getPaginatedTodo)
                .then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        softAssertions.assertThat(200).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Правильное обновление текста задач по Id")
    @Description(value = "Обновляю текст задачи на другой валидный текст ")
    @Test
    public void patchTodoTestTextForId(){
        methods.loginDefault();
        Response response = methods.todoCreate();
        int id = response.jsonPath().getInt("data.id");
        requestBody.put("text", text);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        response = request.log().all().patch(routes.patchText + id)
                .then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        softAssertions.assertThat(200).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertAll();
    }


    @Epic(value = "Работа с Todo")
    @Feature(value = "Неправильное обновление статуса задач по Id")
    @Description(value = "Передаю в тело запроса невалидный статус ")
    @Test
    public void negativePatchTodoTestForId(){
        methods.loginDefault();
        Response response = methods.todoCreate();
        int id = response.jsonPath().getInt("data.id");
        requestBody.put("status", "Бублик");
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        response = request.log().all().patch(routes.patchTodo + id)
                .then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");

        softAssertions.assertThat(400).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertThat(errorCode.HTTP_MESSAGE_NOT_READABLE_EXCEPTION).isEqualTo(customStatusCode);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Неправильное обновление статуса всех задач")
    @Description(value = "Ошибка с кодировкой статуса")
    @Test
    public void negativePatchTodoTestAll(){
        methods.loginDefault();
        methods.todoCreate();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        requestBody.put("status", status);

        Response response = request.log().all().patch(routes.getPaginatedTodo)
                .then().contentType(ContentType.JSON)
                .log().all().extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");

        softAssertions.assertThat(400).isEqualTo(statusCode);
        softAssertions.assertThat("true").isEqualTo(success);
        softAssertions.assertThat(errorCode.TODO_STATUS_NOT_NULL).isEqualTo(customStatusCode);
        softAssertions.assertAll();
    }

    @Epic(value = "Работа с Todo")
    @Feature(value = "Неправильное обновление текста задач по Id")
    @Description(value = "Обновляю текст задачи на пустой текст")
    @Test
    public void negativePatchTodoTestTextForId(){
        methods.loginDefault();
        Response response = methods.todoCreate();
        int id = response.jsonPath().getInt("data.id");
        requestBody.put("text", "");
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        response = request.log().all().patch(routes.patchText + id)
                .then().contentType(ContentType.JSON)
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

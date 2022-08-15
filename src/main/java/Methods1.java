import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Random;
public class Methods1 {
    Routes routes = new Routes();
    JSONObject requestBody = new JSONObject();
    RequestSpecification request = RestAssured.given();


    String defaultEmail = "Sasasa@mail.ru";
    String defaultPassword = "111222";

    String text = Methods1.generateRandomHexString(40);


    public static String generateRandomHexString(int length) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, length);
    }



    public Response loginDefault() {
        requestBody.put("email", defaultEmail);
        requestBody.put("password", defaultPassword);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        return request.post(routes.postLogin1).then().
                contentType(ContentType.JSON).extract().response();
    }

    public Response todoCreate() {
        requestBody.put("text", text);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        return request.post(routes.todoCreate).then().log().all().contentType(ContentType.JSON)
                .extract().response();
    }
}
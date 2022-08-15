import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Random;


public class Methods {
    RegistrationResponse registrationResponse = new RegistrationResponse();
    JSONObject requestBody = new JSONObject();
    String avatar = "src/main/resources/avatar1.jpeg";
    String email = generateRandomHexString(5) + "@mail.ru";
    String name = "Viktoria";
    String password = "Lokon";
    String role = "user";
    String token = registrationResponse.getToken();


    public Response registrationPage() {

        RegistrationPage registrationPage = new RegistrationPage(avatar, email, name, password, role);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        return request.body(registrationPage)
                .post(routes.postRegistration)
                .then().log().all().
                contentType(ContentType.JSON)
                .extract().response();
    }

    Routes routes = new Routes();
    RequestSpecification request = RestAssured.given();
    public static String generateRandomHexString (int length){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < length){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, length);
    }

    public static void showBodyPostLogin(RequestSpecification request, Routes routes) {
        request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response().prettyPrint();
    }

}

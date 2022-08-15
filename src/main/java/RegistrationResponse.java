import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RegistrationResponse {

    private String avatar;
    private String email;
    private String name;
    private String role;
    private String id;


    public Integer getStatusCode() {
        return statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    String token;
    private Integer statusCode;
    private Boolean success;

    public RegistrationResponse(String avatar, String email, String name, String role, String id, String token, Integer statusCode, Boolean success) {
        this.avatar = avatar;
        this.email = email;
        this.name = name;
        this.role = role;
        this.id = id;
        this.token = token;
        this.statusCode = statusCode;
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


    public String getRole() {
        return role;
    }

    public RegistrationResponse(){

    }

}

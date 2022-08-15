import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationPage {
    private String avatar;
    private String email;
    private String name;
    private String password;
    private String role;

    public RegistrationPage(String avatar, String email, String name, String password, String role) {
        this.avatar = avatar;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;

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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public RegistrationPage(){

    }

}























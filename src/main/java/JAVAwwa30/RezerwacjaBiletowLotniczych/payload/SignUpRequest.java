package JAVAwwa30.RezerwacjaBiletowLotniczych.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String login;

    @NotBlank
    private String userFirstName;

    @NotBlank
    private String userLastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignUpRequest(String login, String userFirstName, String userLastName, String email,  String password) {
        this.login = login;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.email = email;
        this.role = null;
        this.password = password;
    }

    public SignUpRequest() {
    }
}
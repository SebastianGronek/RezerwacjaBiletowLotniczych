package JAVAwwa30.RezerwacjaBiletowLotniczych.payload;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;/*
    private String userFirstName;
    private String userLastName;  //Czy to jest konieczne w JwtResponse?*/
    private String login;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, /*String userFirstName, String userLastName,*/ String login, String email, List<String> roles) {
        this.token = token;
        this.id = id;/*
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;*/
        this.login = login;
        this.email = email;
        this.roles = roles;
    }
}

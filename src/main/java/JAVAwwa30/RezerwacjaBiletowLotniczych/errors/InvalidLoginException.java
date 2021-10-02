package JAVAwwa30.RezerwacjaBiletowLotniczych.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid login")
public class InvalidLoginException extends Exception{
    public InvalidLoginException(String message) {
        super(message);
    }
}

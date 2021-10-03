package JAVAwwa30.RezerwacjaBiletowLotniczych.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Location not found")
public class InvalidLocationException extends RuntimeException{
    public InvalidLocationException(String message) {
        super(message);
    }
}


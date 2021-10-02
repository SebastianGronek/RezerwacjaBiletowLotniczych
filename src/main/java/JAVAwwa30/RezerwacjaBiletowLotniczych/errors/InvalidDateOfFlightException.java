package JAVAwwa30.RezerwacjaBiletowLotniczych.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Date of flight is invalid")
public class InvalidDateOfFlightException extends Exception{
    public InvalidDateOfFlightException(String message) {
        super(message);
    }
}
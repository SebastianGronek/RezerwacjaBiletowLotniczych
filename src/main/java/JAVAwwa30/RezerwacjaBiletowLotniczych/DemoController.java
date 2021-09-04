package JAVAwwa30.RezerwacjaBiletowLotniczych;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/start")
    public String getIndex() {
        return "index";
    }
}

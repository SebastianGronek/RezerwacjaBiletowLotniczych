package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightImporter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final FlightImporter flightImporter;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }


    @GetMapping("/populateDatabase")
    public void getFlights() {
        flightImporter.importFlightsFromExternalApi();
    }

    @Transactional
    @DeleteMapping("/deleteRecords")
    public void deleteFlights() {
        flightImporter.deleteOldestDataFromDatabase();
    }

}

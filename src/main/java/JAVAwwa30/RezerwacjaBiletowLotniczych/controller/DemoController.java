package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightImporter;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        flightImporter.importFlightsFromChosenAirport();
    }


}

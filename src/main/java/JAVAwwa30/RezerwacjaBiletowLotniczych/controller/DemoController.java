package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.Repository.JPARepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    JPARepository jpaRepository;

    @GetMapping("/start")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/mockFlight")
    public String getMockFlight() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Flight> flightList = jpaRepository.findAll();
        for (Flight flight : flightList) {
            stringBuilder.append(flight);
        }
        return stringBuilder.toString();
    }

    @GetMapping("/findFlight/{startingLocalization}/{destination}")
    public List<Flight> getFlightFromOneDestinationToAnother(@PathVariable String startingLocalization, @PathVariable String destination) {
        return jpaRepository.findFlightByStartingLocationAndDestination(startingLocalization, destination);
    }
}

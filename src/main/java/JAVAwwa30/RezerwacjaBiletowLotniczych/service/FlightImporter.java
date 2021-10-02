package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.FlightDTO;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FlightImporter {
    private final RestTemplate restTemplate;

    private final FlightRepository flightRepository;

    @Scheduled(fixedDelay = 84_000_000)

    private void importFlightsFromExternalAPI() {
        String uri = "";
        FlightDTO[] flightDTO = restTemplate.getForObject(uri, FlightDTO[].class);

    }
}

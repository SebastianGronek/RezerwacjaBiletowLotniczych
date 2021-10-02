package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.FlightDTO;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightImporter {

    private final RestTemplate restTemplate = new RestTemplate();

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper = new FlightMapper();


    @Scheduled(cron = "0 0 13 * * ?")

    public void importFlightsFromChosenAirport() {
        String uri = "http://api.aviationstack.com/v1/flights?access_key={apiAccessKey}";
        String apiAccessKey = "529c0f2244e1dbe35c9acc21cb998925";
        FlightDTO flightDTO = restTemplate.getForObject(uri, FlightDTO.class, apiAccessKey);
        List<Flight> flightList = flightMapper.convertDataToListOfFlights(flightDTO);
        flightRepository.saveAll(flightList);
    }
}

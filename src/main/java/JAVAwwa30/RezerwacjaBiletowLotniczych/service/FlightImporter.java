package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.CannotImportFlightsFromExternalDatabaseException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.FlightDTO;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightImporter {

    private final RestTemplate restTemplate = new RestTemplate();

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper = new FlightMapper();

    @Scheduled(cron = "0 0 13 * * ?")
    public void importFlightsFromExternalApi() {
        String uri = "http://api.aviationstack.com/v1/flights?access_key={apiAccessKey}";
        String apiAccessKey = "529c0f2244e1dbe35c9acc21cb998925";
        FlightDTO flightDTO = restTemplate.getForObject(uri, FlightDTO.class, apiAccessKey);
        if (flightDTO != null) {
            List<Flight> flightList = flightMapper.convertDataToListOfFlights(flightDTO);
            flightRepository.saveAll(flightList);
        } else {
            throw new CannotImportFlightsFromExternalDatabaseException();
        }
    }

    @Scheduled(cron = "0 0 0 20 * ?")
    public void deleteOldestDataFromDatabase() {
//        flightRepository.deleteAllByDateOfFlightBefore(LocalDateTime.now().plusHours(5));
        flightRepository.deleteAllByDateOfFlightBefore(LocalDateTime.now().minusMonths(1));
    }
}

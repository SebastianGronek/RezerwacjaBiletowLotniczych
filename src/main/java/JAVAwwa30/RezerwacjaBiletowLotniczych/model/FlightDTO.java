package JAVAwwa30.RezerwacjaBiletowLotniczych.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDTO {
    List<Data> data;

    @lombok.Data
    public static class Data {
        Departure departure;
        Arrival arrival;
    }
    @lombok.Data
    public static class Departure {
        String airport;
        String scheduled;
    }
   @lombok.Data
    public static class Arrival {
        String airport;
        String scheduled;
    }
}

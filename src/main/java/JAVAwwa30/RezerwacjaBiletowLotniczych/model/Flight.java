package JAVAwwa30.RezerwacjaBiletowLotniczych.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Flight {

    @Id
    private Long id;
    private String start;
    private String end;
    private String time;

}

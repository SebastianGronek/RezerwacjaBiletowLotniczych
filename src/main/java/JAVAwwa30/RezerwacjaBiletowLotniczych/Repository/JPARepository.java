package JAVAwwa30.RezerwacjaBiletowLotniczych.Repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepository extends JpaRepository<Flight, Long> {

}


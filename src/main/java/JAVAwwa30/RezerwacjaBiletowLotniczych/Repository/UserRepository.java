package JAVAwwa30.RezerwacjaBiletowLotniczych.Repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

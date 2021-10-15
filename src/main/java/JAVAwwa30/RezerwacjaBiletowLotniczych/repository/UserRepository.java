package JAVAwwa30.RezerwacjaBiletowLotniczych.repository;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);

}

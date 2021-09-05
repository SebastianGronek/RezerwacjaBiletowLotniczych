package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.Repository.UserRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addUser")
    private User addUser(User user) {
        return userRepository.save(user);
    }

    @PostMapping("/createUser")
    User createUser(String userFirstName, String userName, List<Flight> ticketList){
        return new User(1L, userFirstName, userName, ticketList);
    }
}

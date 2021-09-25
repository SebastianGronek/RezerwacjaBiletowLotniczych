package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.Repository.UserRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.Flight;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addUser")
    public User addUser(User user) {
        return userRepository.save(user);
    }

   /* @PostMapping("/createUser")
    public User createUser(String userFirstName, String userName) {
        return addUser(new User(userFirstName, userName));
    }*/

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

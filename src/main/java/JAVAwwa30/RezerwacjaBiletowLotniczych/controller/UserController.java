package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLoginException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws InvalidLoginException {
        return userService.addUser(user);

    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<User> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) throws InvalidLoginException {
        return userService.updateUser(id, user);
    }

}

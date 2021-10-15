package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLoginException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User addUser(User user) throws InvalidLoginException {
        if (loginValidation(user.getLogin())) {
            throw new InvalidLoginException("Login unavailable. Please choose another login");
        }
        return userRepository.save(user);
    }

    public Optional<User> deleteUser(long id) {
        Optional<User> user = getUserById(id);
        user.ifPresent(value -> userRepository.delete(value));
        return user;
    }

    public User updateUser(long id, User user) throws InvalidLoginException {
        Optional<User> userInDatabase = getUserById(id);
        if (userInDatabase.isEmpty()) {
            return addUser(user);
        }
        User presentUser = userInDatabase.get();
        if (!user.getUserFirstName().equals(presentUser.getUserFirstName())) {
            presentUser.setUserFirstName(user.getUserFirstName());
        }
        if (!user.getUserLastName().equals(presentUser.getUserLastName())) {
            presentUser.setUserLastName(user.getUserLastName());
        }
        if (!user.getLogin().equals(presentUser.getLogin())) {
            if (loginValidation(user.getLogin())) {
                throw new IllegalArgumentException("Login unavailable. Please choose another login");
            }
            presentUser.setLogin(user.getLogin());
        }
        return addUser(presentUser);
    }

    private boolean loginValidation(String login) {
        return !userRepository.existsByLogin(login);
    }
}

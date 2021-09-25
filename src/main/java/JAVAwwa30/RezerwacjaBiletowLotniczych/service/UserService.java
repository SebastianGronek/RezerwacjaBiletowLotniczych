package JAVAwwa30.RezerwacjaBiletowLotniczych.service;

import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> deleteUser(long id) {
        Optional<User> user = getUserById(id);
        if (getUserById(id).isPresent()) {
            userRepository.delete(getUserById(id).get());
        }
        return user;
    }

    public User updateUser(long id, User user) {
        Optional<User> userInDatabase = getUserById(id);
        if (userInDatabase.isEmpty()) {
            return addUser(user);
        }
        User presentUser = userInDatabase.get();
        if (!user.getUserFirstName().equals(presentUser.getUserFirstName())) {
            presentUser.setUserFirstName(user.getUserFirstName());
        }
        if (!user.getUserName().equals(presentUser.getUserName())) {
            presentUser.setUserName(user.getUserName());
        }
        return addUser(presentUser);
    }

}

package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.UserRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserRepository userRepository;

    @Test
    void shouldCreateNewUser() {
        //given
        User userTest = new User("Stefan", "Nowak");
        when(userRepository.save(userTest)).thenReturn(new User(1L,"Stefan", "Nowak", List.of()));

        //when
        User userToMethod = new User("Stefan", "Nowak");
        User user = userController.addUser(userToMethod);

        //then
        assertNotNull(user.getUserId());

    }
}
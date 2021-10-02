package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLoginException;
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

//    @Test
//    void shouldCreateNewUser() throws InvalidLoginException {
//        //given
//        User userTest = new User("Stefan", "Nowak", "StefanNOWAK");
//        when(userRepository.save(userTest)).thenReturn(new User(1L,"Stefan", "Nowak", "StefanNOWAK", List.of()));
//
//        //when
//        User userToMethod = new User("Stefan", "Nowak", "StefanNOWAK");
//        User user = userController.addUser(userToMethod);
//
//        //then
//        assertNotNull(user.getUserId());
//
//    }
}
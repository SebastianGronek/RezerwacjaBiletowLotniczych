package JAVAwwa30.RezerwacjaBiletowLotniczych.controller;

import JAVAwwa30.RezerwacjaBiletowLotniczych.errors.InvalidLoginException;
import JAVAwwa30.RezerwacjaBiletowLotniczych.repository.UserRepository;
import JAVAwwa30.RezerwacjaBiletowLotniczych.model.User;
import JAVAwwa30.RezerwacjaBiletowLotniczych.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);

    UserService userService = new UserService(userRepository);


    @Test
    void shouldAddNewUser() {
        //given
        User testUser = new User("Karol", "Kowalski", "KK");
        when(userRepository.save(testUser)).thenReturn(new User(1L,"Karol", "Kowalski", "KK"));

        //when
        when(userRepository.findLogin()).thenReturn(List.of("KOK"));
        User user = userService.addUser(new User("Karol", "Kowalski", "KK"));

        //then
        assertNotNull(user.getUserId());
    }

    void shouldGetConnectedFlight(){
        //given

    }





}


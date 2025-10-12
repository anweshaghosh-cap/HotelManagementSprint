package hotelmanagement.HotelManagementDemo;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import hotelmanagement.HotelManagementDemo.model.Role;
import hotelmanagement.HotelManagementDemo.model.User;
import hotelmanagement.HotelManagementDemo.repository.RoleRepository;
import hotelmanagement.HotelManagementDemo.repository.UserRepository;
import hotelmanagement.HotelManagementDemo.service.AuthService;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");

        Role role = new Role();
        role.setRoleId(1L);
        role.setName("USER");

        Mockito.when(userRepository.existsByUsername("testuser")).thenReturn(false);
        Mockito.when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = authService.registerUser(user, "USER");
        Assertions.assertEquals("testuser", result.getUsername());
    }
}

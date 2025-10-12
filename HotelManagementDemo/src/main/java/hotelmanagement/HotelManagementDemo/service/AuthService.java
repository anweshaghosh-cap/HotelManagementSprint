package hotelmanagement.HotelManagementDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Role;
import hotelmanagement.HotelManagementDemo.model.User;
import hotelmanagement.HotelManagementDemo.repository.RoleRepository;
import hotelmanagement.HotelManagementDemo.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerUser(User user, String roleName) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResourceNotFoundException("ADDFAILS","Username already exists");
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS","Role not found"));

        user.setRole(role);
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS","User not found"));

        if (!user.getPassword().equals(password)) {
            throw new ResourceNotFoundException("GETFAILS","Invalid credentials");
        }

        return user;
    }
}
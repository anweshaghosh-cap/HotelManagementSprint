//package hotelmanagement.HotelManagementDemo.service;
//
//import hotelmanagement.HotelManagementDemo.model.User;
//import hotelmanagement.HotelManagementDemo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        return new org.springframework.security.core.userdetails.User(
//            user.getUsername(),
//            user.getPassword(),
//            Collections.emptyList()
//        );
//    }
//}
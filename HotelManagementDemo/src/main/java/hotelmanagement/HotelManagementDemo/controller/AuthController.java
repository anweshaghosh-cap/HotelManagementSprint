//package hotelmanagement.HotelManagementDemo.controller;
//
//import hotelmanagement.HotelManagementDemo.config.JwtUtils;
//import hotelmanagement.HotelManagementDemo.model.AuthRequest;
//import hotelmanagement.HotelManagementDemo.model.AuthResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//        );
//
//        String token = jwtUtils.generateJwtToken(authentication);
//        return new AuthResponse(token);
//    }
//}
//package hotelmanagement.HotelManagementDemo.config;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    private final long expirationMs = 86400000; // 1 day
//
//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public String generateJwtToken(Authentication authentication) {
//        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
//        return Jwts.builder()
//                .setSubject(userPrincipal.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean validateJwtToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//
//    public String getUsernameFromJwtToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//}
package com.example.projBLSS.utils;

import com.example.projBLSS.beans.RefreshToken;
import com.example.projBLSS.beans.User;
import com.example.projBLSS.dto.TokenObject;
import com.example.projBLSS.exceptions.UserNotFoundException;
import com.example.projBLSS.repository.RefreshTokenRepository;
import com.example.projBLSS.service.ShutterstockUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static io.jsonwebtoken.lang.Strings.hasText;

@Component
public class JWTutils {

    private static final String AUTHORIZATION = "Authorization";
    @Value("$(jwt.secret)")
    private String jwtSecret;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private ShutterstockUserDetailsService userService;

    public String generateToken(String login){
        Date date = new Date(System.currentTimeMillis() + 20000);
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String login){
        Date date = Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            System.out.println("Token expired");
            throw expEx;
        } catch (UnsupportedJwtException unsEx) {
            System.out.println("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            System.out.println("Malformed jwt");
        } catch (SignatureException sEx) {
            System.out.println("Invalid signature");
            throw sEx;
        } catch (Exception e) {
            System.out.println("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getTokenFromRequest(HttpServletRequest request){
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }

    public ResponseEntity<TokenObject> refreshToken(String refreshToken)  {
        String login = getLoginFromToken(refreshToken);
        try {
            User user = userService.findByLogin(login);
            RefreshToken refreshTokenFromDB = refreshTokenRepository.findById(user.getID()).get();
            if (!refreshToken.equals(refreshTokenFromDB.getRefreshToken())){
                throw new SignatureException("Token not equals");
            }
            refreshToken = generateRefreshToken(login);
            refreshTokenFromDB.setRefreshToken(refreshToken);
            refreshTokenRepository.save(refreshTokenFromDB);
            return new ResponseEntity<>(new TokenObject(generateToken(login), refreshToken), HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            e.setErrMessage("Пользовател по данному токену не существует! Доступ запрещен");
            e.setErrStatus(HttpStatus.BAD_REQUEST);
            throw new SignatureException("User doesn't exist");
        }

    }
}

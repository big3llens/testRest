package ru.markelov.happy.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.markelov.happy.shop.beans.JwtTokenUtil;
import ru.markelov.happy.shop.dto.JwtRequest;
import ru.markelov.happy.shop.dto.JwtResponse;
import ru.markelov.happy.shop.dto.RequestAuth;
import ru.markelov.happy.shop.exceptions.MarketError;
import ru.markelov.happy.shop.models.User;
import ru.markelov.happy.shop.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new RequestAuth(token, userDetails.getUsername()));
    }

//    @PostMapping("/create")
//    public void createNewUser(@RequestBody JwtRequest authRequest, @RequestParam("email") String email) {
//        User user = new User(authRequest.getUsername(), authRequest.getPassword(), email);
//        System.out.println(user.toString());
//        userService.createNewUser(user);
//    }

}

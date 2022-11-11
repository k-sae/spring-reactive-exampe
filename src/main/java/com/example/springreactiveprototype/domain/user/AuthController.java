package com.example.springreactiveprototype.domain.user;

import com.example.springreactiveprototype.domain.user.payload.AuthRequest;
import com.example.springreactiveprototype.domain.user.payload.AuthResponse;
import com.example.springreactiveprototype.domain.user.service.UserService;
import com.example.springreactiveprototype.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private JWTUtil jwtUtil;
//    private PBKDF2Encoder passwordEncoder;
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        return userService.findByName(authRequest.getUsername())
                .filter(userDetails -> authRequest.getRollNumber() == userDetails.getRollNumber())
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}

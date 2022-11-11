package com.example.springreactiveprototype.domain.user;

import com.example.springreactiveprototype.domain.user.model.User;
import com.example.springreactiveprototype.domain.user.model.UserStatusEnum;
import com.example.springreactiveprototype.domain.user.payload.DeleteUserRequestDTO;
import com.example.springreactiveprototype.domain.user.payload.SaveUserRequestDTO;
import com.example.springreactiveprototype.domain.user.service.UserService;
import com.example.springreactiveprototype.exception.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<Flux<User>>getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(UserStatusEnum.ACTIVE), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Mono<User>> saveUser(@Validated @RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        User user = mapper.map(saveUserRequestDTO, User.class);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<User>> updateUser(@PathVariable("id") String id, SaveUserRequestDTO saveUserRequestDTO) {
        User user = mapper.map(saveUserRequestDTO, User.class);
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("")
    public Mono<User> deleteUser(@Validated @RequestBody DeleteUserRequestDTO deleteUserRequestDTO) {
        // usually we should return a response body with the deleted user
        // and add validation to check if the user exists
        return userService.getUserByRollNumberAndGrade(deleteUserRequestDTO.getRollNumber(), deleteUserRequestDTO.getGrade())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User", "rollNumber", deleteUserRequestDTO.getRollNumber())))
                .flatMap(userService::deleteUser);
    }


}

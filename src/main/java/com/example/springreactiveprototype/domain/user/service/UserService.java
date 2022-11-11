package com.example.springreactiveprototype.domain.user.service;

import com.example.springreactiveprototype.domain.user.model.User;
import com.example.springreactiveprototype.domain.user.model.UserStatusEnum;
import com.example.springreactiveprototype.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Flux<User> getAllUsers(UserStatusEnum userStatusEnum) {
        return userRepository.findAllByStatus(userStatusEnum);
    }

    public Mono<User> saveUser(User user) {
        user.setStatus(UserStatusEnum.ACTIVE);
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    public Mono<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> updateUser(User user) {
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

    public Mono<User> deleteUser(String id) {
        return getUser(id).flatMap(this::deleteUser);
    }

    public Mono<User> deleteUser(User user) {
        user.setStatus(UserStatusEnum.DELETED);
        return updateUser(user);
    }

    public Mono<User> getUserByRollNumber(int rollNumber) {
        return userRepository.findFirstByRollNumber(rollNumber);
    }

    public Mono<User> getUserByRollNumberAndGrade(int rollNumber, int grade) {
        return userRepository.findFirstByRollNumberAndGrade(rollNumber, grade);
    }
    public Mono<Boolean> userExistsByRollNumberAndGrade(int rollNumber, int grade) {
        return getUserByRollNumberAndGrade(rollNumber, grade).flatMap(user -> Mono.just(true)).switchIfEmpty(Mono.just(false));
    }

}

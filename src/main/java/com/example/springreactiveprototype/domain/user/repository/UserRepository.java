package com.example.springreactiveprototype.domain.user.repository;

import com.example.springreactiveprototype.domain.user.model.User;
import com.example.springreactiveprototype.domain.user.model.UserStatusEnum;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findFirstByRollNumber(int rollNumber);
    Mono<User> findFirstByRollNumberAndGrade(int rollNumber, int grade);
    Flux<User> findAllByStatus(UserStatusEnum status);

    Mono<User> findFirstByName(String name);
}

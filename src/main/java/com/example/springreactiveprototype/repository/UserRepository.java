package com.example.springreactiveprototype.repository;

import com.example.testspringreactive.domain.user.model.User;
import com.example.testspringreactive.domain.user.model.UserStatusEnum;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findFirstByRollNumber(int rollNumber);
    Mono<User> findFirstByRollNumberAndGrade(int rollNumber, int grade);
    Flux<User> findAllByStatus(UserStatusEnum status);
}

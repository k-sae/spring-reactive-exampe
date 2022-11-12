package com.example.springreactiveprototype.domain.service;

import com.example.springreactiveprototype.domain.user.model.Result;
import com.example.springreactiveprototype.domain.user.model.ResultRemarksEnum;
import com.example.springreactiveprototype.domain.user.model.User;
import com.example.springreactiveprototype.domain.user.payload.SaveUserRequestDTO;
import com.example.springreactiveprototype.domain.user.repository.ResultRepository;
import com.example.springreactiveprototype.domain.user.service.ResultService;
import com.example.springreactiveprototype.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestResultService {
    @MockBean
    private ResultRepository resultRepository;
    private ResultService resultService;

    @BeforeEach
    public void setUp() {
        resultService = new ResultService(resultRepository);
    }

    @Test
    public void addResultTest() {

        when(resultRepository.countByObtainedMarksIsGreaterThan(anyDouble()))
                .thenReturn(Mono.just(1L));
        when(resultRepository.save(any())).thenReturn(
                Mono.just(Result.builder().remarks(ResultRemarksEnum.PASS).positionInClass(2L).build()
                ));
        when(resultRepository.findByObtainedMarksLessThanEqual(anyDouble()))
                .thenReturn(Flux.just(Result.builder().remarks(ResultRemarksEnum.PASS).positionInClass(2L).build()));
        Mono<Result> result = resultService.saveResult(Result.builder().obtainedMarks(6).totalMarks(10).build());
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(p -> p.getRemarks() == ResultRemarksEnum.PASS && p.getPositionInClass() == 2L)
                .verifyComplete();
    }

    @Test
    public void increaseStudentPositionsTest() {
        when(resultRepository.findByObtainedMarksLessThanEqual(anyDouble()))
                .thenReturn(Flux.just(
                        Result.builder().positionInClass(2L).build(),
                        Result.builder().positionInClass(3L).build(),
                        Result.builder().positionInClass(4L).build())
                );
        when(resultRepository.save(any(Result.class))).thenAnswer(a -> Mono.just(a.getArgument(0)));

        Flux<Result> result = resultService.increaseStudentPositions(2D);
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(p -> p.getPositionInClass() == 3L)
                .expectNextMatches(p -> p.getPositionInClass() == 4L)
                .expectNextMatches(p -> p.getPositionInClass() == 5L)
                .verifyComplete();
    }
}

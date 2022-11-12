package com.example.springreactiveprototype.domain.user.service;

import com.example.springreactiveprototype.domain.user.model.Result;
import com.example.springreactiveprototype.domain.user.model.ResultRemarksEnum;
import com.example.springreactiveprototype.domain.user.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@AllArgsConstructor
@Component
public class ResultService {
    private final ResultRepository resultRepository;

    @SuppressWarnings("FieldCanBeLocal")
    private static final double PASSING_MARK_PERCENTAGE = 0.5;

    public Flux<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Mono<Result> saveResult(Result result) {
        if (result.getObtainedMarks() / result.getTotalMarks() >= PASSING_MARK_PERCENTAGE) {
            result.setRemarks(ResultRemarksEnum.PASS);
        } else {
            result.setRemarks(ResultRemarksEnum.FAIL);
        }
        result.setCreatedAt(new Date());
        return getPositionInClass(result).flatMap(position -> {
                    result.setPositionInClass(position);
            increaseStudentPositions(result.getObtainedMarks()).subscribe();
                    return updateResult(result);
                }
        );

    }

    public Flux<Result> increaseStudentPositions(double getObtainedMarks) {
        return resultRepository.findByObtainedMarksLessThanEqual(getObtainedMarks).flatMap(result -> {
                // async update
                result.setPositionInClass(result.getPositionInClass() + 1);
            return  resultRepository.save(result);
        });
    }

    public Mono<Long> getPositionInClass(Result result) {
        return resultRepository.countByObtainedMarksIsGreaterThan(result.getObtainedMarks()).map(count -> count + 1);
    }


    public Mono<Result> getResult(String id) {
        return resultRepository.findById(id);
    }

    public Mono<Result> updateResult(Result result) {
        result.setUpdateAt(new Date());
        return resultRepository.save(result);
    }

    public Mono<Void> deleteResult(String id) {
        return resultRepository.deleteById(id);
    }

    public Mono<Result> getResultByRollNumber(int rollNumber) {
        return resultRepository.findFirstByRollNumber(rollNumber);
    }


}

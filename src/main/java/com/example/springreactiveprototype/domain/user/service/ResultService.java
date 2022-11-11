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
                    return resultRepository.save(result);
                }
        );

    }

    public Mono<Void> increaseStudentPositions(double getObtainedMarks) {
        return resultRepository.findByObtainedMarksLessThanEqual(getObtainedMarks).collectList().flatMap(results -> {
            results.forEach(result1 -> {
                // async update
                result1.setPositionInClass(result1.getPositionInClass() + 1);
                resultRepository.save(result1).subscribe();
            });
            return Mono.empty();
        });
    }

    public Mono<Long> getPositionInClass(Result result) {
        return resultRepository.countByObtainedMarksIsGreaterThan(result.getObtainedMarks()).map(count -> count + 1);
    }


    public Mono<Result> getResult(String id) {
        return resultRepository.findById(id);
    }

    public Mono<Result> updateResult(Result result) {
        return resultRepository.save(result);
    }

    public Mono<Void> deleteResult(String id) {
        return resultRepository.deleteById(id);
    }

    public Mono<Result> getResultByRollNumber(int rollNumber) {
        return resultRepository.findFirstByRollNumber(rollNumber);
    }


}

package com.example.springreactiveprototype.domain.user;


import com.example.springreactiveprototype.domain.user.model.Result;
import com.example.springreactiveprototype.domain.user.payload.SaveResultRequestDTO;
import com.example.springreactiveprototype.domain.user.service.ResultService;
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
@RequestMapping("/results")
public class ResultController {
    private final ResultService resultService;
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<Flux<Result>> getAllResults() {
        return new ResponseEntity<>(resultService.getAllResults(), HttpStatus.OK);
    }


    @PostMapping("")
    public Mono<Result> saveResult(@Validated @RequestBody SaveResultRequestDTO saveResultRequestDTO) {
        // validate user exists first
        return userService.getUserByRollNumberAndGrade(saveResultRequestDTO.getRollNumber(), saveResultRequestDTO.getGrade())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User", "rollNumber", saveResultRequestDTO.getRollNumber())))
                .then(resultService.saveResult(mapper.map(saveResultRequestDTO, Result.class)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Result>> getResult(@PathVariable String id) {
        return new ResponseEntity<>(resultService.getResult(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Result>> updateResult(Result result) {
        return new ResponseEntity<>(resultService.updateResult(result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> deleteResult(@PathVariable String id) {
        return new ResponseEntity<>(resultService.deleteResult(id), HttpStatus.OK);
    }


}

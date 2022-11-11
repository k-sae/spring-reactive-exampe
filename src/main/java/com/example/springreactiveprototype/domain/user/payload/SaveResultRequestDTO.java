package com.example.springreactiveprototype.domain.user.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveResultRequestDTO {

    @NotNull
    @Min(1)
    @Max(100)
    private double totalMarks;
    @NotNull
    @Min(1)
    @Max(100)
    private double obtainedMarks;
    @NotNull
    @Min(1)
    @Max(100)
    private int rollNumber;
    @NotNull
    @Min(1)
    @Max(100)
    private int grade;
}

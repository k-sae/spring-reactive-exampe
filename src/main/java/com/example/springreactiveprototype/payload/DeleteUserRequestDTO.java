package com.example.springreactiveprototype.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequestDTO {

    @NotNull
    @Min(1)
    @Max(100)
    private int rollNumber;
    @NotNull
    @Min(1)
    @Max(100)
    private int grade;
}

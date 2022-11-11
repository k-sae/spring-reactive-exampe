package com.example.springreactiveprototype.domain.user.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequestDTO {

    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    @Min(1)
    @Max(100)
    private int rollNumber;
    @NotNull
    @Size(min = 1, max = 100)
    private String fatherName;
    @NotNull
    @Min(1)
    @Max(100)
    private int grade;
}

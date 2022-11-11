package com.example.springreactiveprototype.domain.user.payload;

import com.example.testspringreactive.domain.user.model.ResultRemarksEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    @Id
    private String id;
    private double totalMarks;
    private double obtainedMarks;
    private int rollNumber;
    private int grade;
    private ResultRemarksEnum remarks;
    private int positionInClass;
    private Date createdAt;
    private Date updateAt;
}

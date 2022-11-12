package com.example.springreactiveprototype.domain.user.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("result")
public class Result {
    @Id
    private String id;
    private double totalMarks;
    private double obtainedMarks;
    private int rollNumber;
    private int grade;
    private ResultRemarksEnum remarks;
    private Long positionInClass;
    private Date createdAt;
    private Date updateAt;
}

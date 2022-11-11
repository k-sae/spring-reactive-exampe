package com.example.springreactiveprototype.domain.user.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("user")
public class User {
    @Id
    private String id;
    private String name;
    private int rollNumber;
    private String fatherName;
    private int grade;
    private UserStatusEnum status;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;

}

package com.example.springreactiveprototype.domain.user.payload;


import com.example.testspringreactive.domain.user.model.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private int rollNumber;
    private String fatherName;
    private int grade;
    private UserStatusEnum status;
    private Date createdAt;
    private Date updateAt;
}

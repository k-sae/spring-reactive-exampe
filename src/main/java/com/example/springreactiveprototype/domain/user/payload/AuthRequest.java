package com.example.springreactiveprototype.domain.user.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  AuthRequest {
    private String username;
    private int rollNumber;
}
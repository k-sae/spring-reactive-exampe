package com.example.springreactiveprototype.seeder;

import com.example.springreactiveprototype.domain.user.model.User;
import com.example.springreactiveprototype.domain.user.model.UserStatusEnum;
import com.github.javafaker.Faker;

import java.util.Date;

public class UserSeeder {

    public static User getUser() {
        Faker faker = new Faker();
        return User.builder()
                .name(faker.name().username())
                .fatherName(faker.name().lastName())
                .rollNumber(faker.number().numberBetween(1, 100))
                .grade(faker.number().numberBetween(1, 10))
                .status(UserStatusEnum.ACTIVE)
                .createdAt(new Date())
                .build();

    }
    public static User getDefaultUser() {
        Faker faker = new Faker();
        return User.builder()
                .name("defaultStudent")
                .fatherName(faker.name().lastName())
                .rollNumber(1)
                .grade(1)
                .status(UserStatusEnum.ACTIVE)
                .createdAt(new Date())
                .build();

    }

}

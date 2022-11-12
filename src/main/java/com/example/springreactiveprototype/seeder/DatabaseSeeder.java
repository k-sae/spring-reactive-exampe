package com.example.springreactiveprototype.seeder;


import com.example.springreactiveprototype.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseSeeder {
    private final UserRepository userRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
    }

    public void seedUsersTable() {
        userRepository.count().subscribe(count -> {
            userRepository.save(UserSeeder.getDefaultUser()).subscribe();
            if (count < 3) {
                for (int i = 0; i < 3; i++) {
                    userRepository.save(UserSeeder.getUser()).subscribe();
                }
            }
        });
    }
}

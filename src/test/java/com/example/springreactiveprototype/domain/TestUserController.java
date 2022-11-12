package com.example.springreactiveprototype.domain;
import com.example.springreactiveprototype.domain.user.model.User;
import com.example.springreactiveprototype.domain.user.payload.SaveUserRequestDTO;
import com.example.springreactiveprototype.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserController {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UserService service;

    @Test
    public void addUserTest(){
        SaveUserRequestDTO saveUserRequestDTO = new SaveUserRequestDTO(
                "test name",
                1,
                "test father",
                1
        );
        webTestClient.post().uri("/users")
                .body(Mono.just(saveUserRequestDTO), User.class)
                .exchange()
                .expectStatus().isCreated();

    }


    @Test
    public void getUserTest(){

        User expected = new User();
        expected.setName("testUser");
        expected.setId("testId");

        when(service.getUser(any())).thenReturn(Mono.just(expected));

        Flux<User> responseBody = webTestClient.get().uri("/users/testId")
                .exchange()
                .expectStatus().isOk()
                .returnResult(User.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p-> p.getName().equals("testUser"))
                .verifyComplete();

    }
}

package com.telenor.greeting.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {


    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void test_greeting_withUserIdAndPersonalAccount_expect_success() {
        String userId = "123";
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("id", userId)
                        .queryParam("account", "personal")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEqualTo("Hi, userId ".concat(userId));
    }

    @Test
    public void test_greeting_withUserIdAndBusinessAccount_expect_invalid_request() {
        String userId = "123";
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("id", userId)
                        .queryParam("account", "business")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorDescription").isEqualTo("Type is mandatory for Business account");
    }

    @Test
    public void test_greeting_withTypeAndPersonalAccount_expect_invalid_request() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("type", "small")
                        .queryParam("account", "personal")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errorDescription").isEqualTo("Id is mandatory for Personal account");
    }

    @Test
    public void test_greeting_withInvalidUserId_expect_invalid_request() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("id", -1)
                        .queryParam("account", "business")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void test_greeting_withInvalidAccount_expect_invalid_request() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("id", 1)
                        .queryParam("account", "invalid")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void test_greeting_withInvalidUserIdAndType_expect_invalid_request() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("id", "")
                        .queryParam("account", "business")
                        .queryParam("type", "")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void test_greeting_withInvalidType_expect_invalid_request() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("account", "business")
                        .queryParam("type", "invalid")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void test_greeting_withValidTypeAndAccount_expect_success() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("account", "business")
                        .queryParam("type", "big")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEqualTo("Welcome, business user!");
    }

    @Test
    public void test_greeting_withValidTypeAndAccount_expect_unimplementedException() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/greeting/")
                        .queryParam("account", "business")
                        .queryParam("type", "small")
                        .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
    }


}
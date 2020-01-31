package com.telenor.greeting.service;

import com.telenor.greeting.exception.UnImplementedException;
import com.telenor.greeting.model.Account;
import com.telenor.greeting.model.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {

    @InjectMocks
    private GreetingService service;

    @Test
    public void test_greeting_expect_data() {

        Integer userId = 12;
        Mono<String> value = service.greeting(userId, Account.PERSONAL.getValue(), null);

        StepVerifier.create(value)
                .expectNext("Hi, userId ".concat(String.valueOf(userId)))
                .expectComplete()
                .verify();
    }

    @Test(expected = UnImplementedException.class)
    public void test_greeting_expect_exception() {
        Integer userId = 12;
        service.greeting(userId, Account.BUSINESS.getValue(), Type.SMALL.getValue());
    }

}
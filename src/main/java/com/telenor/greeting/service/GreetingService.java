package com.telenor.greeting.service;


import com.telenor.greeting.exception.UnImplementedException;
import com.telenor.greeting.model.Account;
import com.telenor.greeting.model.Type;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.telenor.greeting.model.Account.BUSINESS;
import static com.telenor.greeting.model.Type.SMALL;

@Service
public class GreetingService {

    public Mono<String> greeting(Integer userId, String account, String type) {

        String greeting;

        if(BUSINESS == Account.fromString(account)) {
            if (SMALL == Type.fromString(type)) {
                throw new UnImplementedException("path is not implemented");
            }else {
                greeting = "Welcome, business user!";
            }
        }else{
            greeting = "Hi, userId ".concat(String.valueOf(userId));
        }

        return Mono.just(greeting);
    }
}

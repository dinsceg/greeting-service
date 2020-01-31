package com.telenor.greeting.resources;

import com.telenor.greeting.exception.InvalidRequestException;
import com.telenor.greeting.model.Account;
import com.telenor.greeting.model.Type;
import com.telenor.greeting.service.GreetingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import java.util.Arrays;

import static com.telenor.greeting.model.Account.BUSINESS;
import static com.telenor.greeting.model.Account.PERSONAL;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/greeting")
@AllArgsConstructor
public class GreetingController {

    private final GreetingService service;

    @GetMapping("/")
    @ApiOperation("Get the greeting phrase")
    public Mono<String> greeting(@RequestParam(required = false) @Min(1) Integer id,
                                 @RequestParam @ApiParam(value = "Account", required = true, allowableValues = "personal,business", defaultValue = "personal")
                                         String account,
                                 @RequestParam(required = false) @ApiParam(value = "Type", allowableValues = "small,big", defaultValue = "big")
                                         String type) {

        validateRequest(id, account, type);

        return service.greeting(id, account, type);
    }

    private void validateRequest(Integer id, String account, String type) {
        if ((nonNull(id) && id < 0) ||
                !Arrays.asList(Account.values()).contains(Account.valueOf(account.toUpperCase())) ||
                (nonNull(type) && !Arrays.asList(Type.values()).contains(Type.valueOf(type.toUpperCase())))) {
            throw new InvalidRequestException("invalid request");
        }

        if(PERSONAL == Account.fromString(account) && isNull(id)) {
            throw new InvalidRequestException("Id is mandatory for Personal account");
        }

        if(BUSINESS == Account.fromString(account)  && isNull(type)){
            throw new InvalidRequestException("Type is mandatory for Business account");
        }
    }

}

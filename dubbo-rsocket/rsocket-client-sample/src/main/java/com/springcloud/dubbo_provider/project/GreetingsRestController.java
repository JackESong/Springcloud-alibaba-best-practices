package com.springcloud.dubbo_provider.project;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class GreetingsRestController {

    private final RSocketRequester requester;

   @GetMapping(value = "/gree")
    public String greetStream() {
        return "123";
    }

    @GetMapping("/greet/{name}")
    public Publisher<GreetingsResponse> greet(@PathVariable String name) {
        return requester
                .route("greet")
                .data(new GreetingsRequest(name))
                .retrieveMono(GreetingsResponse.class);
    }


    @GetMapping(value = "/greet-stream/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<GreetingsResponse> greetStream(@PathVariable String name) {
        return requester
                .route("greet-stream")
                .data(new GreetingsRequest(name))
                .retrieveFlux(GreetingsResponse.class);
    }
}

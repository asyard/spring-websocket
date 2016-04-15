package com.asy.test.spring.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")        //  ensures that if a message is sent to destination "/hello", then the greeting() method is called.
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        // simulated delay. his is to demonstrate that after the client sends a message,
        // the server can take as long as it needs to process the message asynchronously.
        // The client may continue with whatever work it needs to do without waiting on the response.
        Thread.sleep(300);

        // return value is broadcast to all subscribers to "/topic/greetings" as specified in the @SendTo annotation.
        return new Greeting("Hello, " + message.getName() + "!");
    }

}

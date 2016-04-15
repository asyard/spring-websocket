package com.asy.test.spring.websocket.v2;

import com.asy.test.spring.websocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomDataGenerator implements ApplicationListener<BrokerAvailabilityEvent> {

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public RandomDataGenerator(final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(final BrokerAvailabilityEvent event) {
        System.out.println("onApplicationEvent");
    }

    @Scheduled(fixedDelay = 5000)
    public void sendDataUpdates() {
        //this.messagingTemplate.convertAndSend("/topic/greetings", new Greeting(commands[new Random().nextInt(5)]));
        String to = admins[new Random().nextInt(2)];
        System.out.println("sending a new command to " + to);

        this.messagingTemplate.convertAndSend("/topic/user/"+ to + "/request", new Greeting(commands[new Random().nextInt(5)]));
    }

    private static String[] commands = {"command1", "command2", "command3", "command4", "command5"};

    private static String[] admins = {"admin1", "admin2"};

}
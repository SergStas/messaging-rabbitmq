package com.example.messagingrabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private final CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(MessageData message) {
        if (MessagingRabbitmqApplication.instanceId != message.getSenderId()) {
            System.out.print("Received <" + message.getContent() + "> from #" + message.getSenderId() + "\n> ");
            latch.countDown();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

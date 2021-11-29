package com.example.messagingrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.print("> ");
            String message = new Scanner(System.in).nextLine();
            if ("-q".equals(message)) {
                break;
            }
            rabbitTemplate.convertAndSend(
                    MessagingRabbitmqApplication.exchangeName,
                    MessagingRabbitmqApplication.exchangeName,
                    new MessageData(MessagingRabbitmqApplication.instanceId, message)
            );
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        }
    }

}

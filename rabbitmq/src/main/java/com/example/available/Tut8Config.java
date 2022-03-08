package com.example.available;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Administrator
 */
@Profile(value = {"tut8", "available"})
@Configuration
public class Tut8Config {
    @Bean("available")
    public Queue available() {
        return new Queue("available");
    }

    @Profile("receiver")
    @Bean
    public Tut8Receiver receiver() {
        return new Tut8Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut8Sender sender() {
        return new Tut8Sender();
    }


}

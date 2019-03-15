package com.hugo.midware.openmessaging.listener;

import com.hugo.midware.openmessaging.annotation.EnableOMS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOMS
public class PushConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushConsumerApplication.class);
    }
}

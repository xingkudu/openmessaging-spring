package com.hugo.midware.openmessaging.producer;

import com.hugo.midware.openmessaging.annotation.EnableOMS;
import com.hugo.midware.openmessaging.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableOMS
public class ProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }

    @Autowired
    private Producer producer;


    @Override
    public void run(String... args) throws Exception {
        for(int i=1; i<=1000;i++){
            producer.send("TopicTest1", ("TopicTest1["+i+"]").getBytes());
            producer.send("TopicTest2", ("TopicTest2["+i+"]").getBytes());
        }

    }
}

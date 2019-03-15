package com.hugo.midware.openmessaging.consumer;

import com.hugo.midware.openmessaging.annotation.EnableOMS;
import com.hugo.midware.openmessaging.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableOMS
public class PullConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PullConsumerApplication.class);
    }

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

    @Autowired
    Consumer consumer;

    @Override
    public void run(String... args) throws Exception {
        PullConsumer run1 = new PullConsumer("TopicTest1", consumer);
        PullConsumer run2 = new PullConsumer("TopicTest2", consumer);
        threadPoolExecutor.submit(run1);
        threadPoolExecutor.submit(run2);
        threadPoolExecutor.submit(() -> {
            System.out.println("TopicTest1 revice message count[" + run1.getCount() + "]");
            System.out.println("TopicTest2 revice message count[" + run2.getCount() + "]");
        });
    }
}

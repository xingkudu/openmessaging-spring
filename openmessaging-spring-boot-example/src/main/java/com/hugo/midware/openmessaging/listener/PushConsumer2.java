package com.hugo.midware.openmessaging.listener;

import com.hugo.midware.openmessaging.annotation.QueueListener;
import io.openmessaging.Message;
import io.openmessaging.consumer.MessageListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PushConsumer
 * @Description TODO
 * @Author hugo
 * @Date 2019-02-26 17:42
 * @Version 1.0
 **/
@QueueListener({"TopicTest2"})
public class PushConsumer2 implements MessageListener {

    AtomicInteger count = new AtomicInteger(0);

    @Override
    public void onReceived(Message message, Context context) {
        System.out.println(new String(message.getBody(byte[].class)) + "," + count.incrementAndGet());
        context.ack();
    }
}

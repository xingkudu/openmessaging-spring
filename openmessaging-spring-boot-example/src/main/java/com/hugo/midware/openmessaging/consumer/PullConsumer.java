package com.hugo.midware.openmessaging.consumer;

import com.hugo.midware.openmessaging.Consumer;
import io.openmessaging.Message;

import java.util.concurrent.atomic.AtomicInteger;

public class PullConsumer implements Runnable {

    private final String queue;

    private final Consumer consumer;

    private final AtomicInteger count = new AtomicInteger(0);

    public PullConsumer(String queue, Consumer consumer) {
        this.queue = queue;
        this.consumer = consumer;
    }

    public int getCount(){
        return count.get();
    }

    @Override
    public void run() {
        this.consumer.attachQueue(queue);
        while(true){
            //pull consumer message
            Message message = consumer.receive();
            if(message != null){
                System.out.println(queue + " revice message[" + new String(message.getBody(byte[].class))+"]");
                count.incrementAndGet();
                if(!new String(message.getBody(byte[].class)).startsWith(queue)){
                    System.out.println("--------------message chaos---------------");
                }
                consumer.ack(message.sysHeaders().getString(Message.BuiltinKeys.MESSAGE_ID));
            } else {
              break;
            }
        }
    }
}

package com.hugo.midware.openmessaging.impl;

import com.hugo.midware.openmessaging.Consumer;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.consumer.PullConsumer;

/**
 * @ClassName ConsumerAdapter
 * @Description PullConsumer 适配
 * @Author hugo
 * @Date 2019-02-25 18:49
 * @Version 1.0
 **/
public class ConsumerAdapter implements Consumer {

    private final PullConsumer consumer;

    public ConsumerAdapter(PullConsumer consumer) {
        this.consumer = consumer;
    }


    @Override
    public KeyValue attributes() {
        return consumer.attributes();
    }

    @Override
    public PullConsumer attachQueue(String s) {
        consumer.attachQueue(s);
        return this;
    }

    @Override
    public PullConsumer attachQueue(String s, KeyValue keyValue) {
        consumer.attachQueue(s, keyValue);
        return this;
    }

    @Override
    public PullConsumer detachQueue(String s) {
        consumer.detachQueue(s);
        return this;
    }

    @Override
    public Message receive() {
        return consumer.receive();
    }

    @Override
    public Message receive(KeyValue keyValue) {
        return consumer.receive(keyValue);
    }

    @Override
    public void ack(String s) {
        consumer.ack(s);
    }

    @Override
    public void ack(String s, KeyValue keyValue) {
        consumer.ack(s, keyValue);
    }

    @Override
    public void startup() {
        consumer.startup();
    }

    @Override
    public void shutdown() {
        consumer.shutdown();
    }
}

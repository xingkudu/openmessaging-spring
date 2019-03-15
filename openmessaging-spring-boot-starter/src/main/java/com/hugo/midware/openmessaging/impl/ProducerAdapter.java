package com.hugo.midware.openmessaging.impl;

import com.hugo.midware.openmessaging.Producer;
import io.openmessaging.BytesMessage;
import io.openmessaging.Future;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.interceptor.ProducerInterceptor;
import io.openmessaging.producer.BatchMessageSender;
import io.openmessaging.producer.LocalTransactionExecutor;
import io.openmessaging.producer.SendResult;

/**
 * @ClassName ProducerAdapter
 * @Description producer适配器
 * @Author hugo
 * @Date 2019-02-25 18:21
 * @Version 1.0
 **/
public class ProducerAdapter implements Producer {

    private final io.openmessaging.producer.Producer producer;

    public ProducerAdapter(io.openmessaging.producer.Producer producer) {
        this.producer = producer;
    }

    @Override
    public KeyValue attributes() {
        return producer.attributes();
    }

    @Override
    public SendResult send(Message message) {
        return producer.send(message);
    }

    @Override
    public SendResult send(Message message, KeyValue keyValue) {
        return producer.send(message, keyValue);
    }

    @Override
    public SendResult send(Message message, LocalTransactionExecutor localTransactionExecutor, KeyValue keyValue) {
        return producer.send(message, localTransactionExecutor, keyValue);
    }

    @Override
    public Future<SendResult> sendAsync(Message message) {
        return producer.sendAsync(message);
    }

    @Override
    public Future<SendResult> sendAsync(Message message, KeyValue keyValue) {
        return producer.sendAsync(message, keyValue);
    }

    @Override
    public void sendOneway(Message message) {
        producer.sendOneway(message);
    }

    @Override
    public void sendOneway(Message message, KeyValue keyValue) {
        producer.sendOneway(message, keyValue);
    }

    @Override
    public BatchMessageSender createBatchMessageSender() {
        return producer.createBatchMessageSender();
    }

    @Override
    public void addInterceptor(ProducerInterceptor producerInterceptor) {
        producer.addInterceptor(producerInterceptor);
    }

    @Override
    public void removeInterceptor(ProducerInterceptor producerInterceptor) {
        producer.removeInterceptor(producerInterceptor);
    }

    @Override
    public BytesMessage createBytesMessage(String s, byte[] bytes) {
        return producer.createBytesMessage(s, bytes);
    }

    @Override
    public void startup() {
        producer.startup();
    }

    @Override
    public void shutdown() {
        producer.shutdown();
    }
}

package com.hugo.midware.openmessaging;

import io.openmessaging.Future;
import io.openmessaging.Message;
import io.openmessaging.producer.SendResult;

public interface Producer extends io.openmessaging.producer.Producer {

    default SendResult send(String queue, byte[] bytes){
        Message message = createBytesMessage(queue, bytes);
        return send(message);
    }

    default Future<SendResult> sendAsync(String queue, byte[] bytes) {
        Message message = createBytesMessage(queue, bytes);
        return sendAsync(message);
    }

    default void sendOneway(String queue, byte[] bytes) {
        Message message = createBytesMessage(queue, bytes);
        sendOneway(message);
    }

}

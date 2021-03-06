package io.openmessaging.demo;

import io.openmessaging.*;

import java.util.Map;

/**
 * Created by lee on 5/16/17.
 */
public class DefaultProducer implements Producer {
    private DefaultMessageFactory messageFactory = new DefaultMessageFactory();
    private KeyValue properties;
    private MessageStore messageStore;

    public DefaultProducer(KeyValue properties) {
        this.properties = properties;
        messageStore = MessageStore.getInstance(properties);
    }
    @Override public BytesMessage createBytesMessageToTopic(String topic, byte[] body) {
        return messageFactory.createBytesMessageToTopic(topic, body);
    }
    @Override public BytesMessage createBytesMessageToQueue(String queue, byte[] body) {
        return messageFactory.createBytesMessageToQueue(queue, body);
    }

    public void flush() {
        // 清空队列和缸里的消息, 所有线程都会调用
        //send(createBytesMessageToTopic("end", "end".getBytes()));
        messageStore.finishCount();

    }

    @Override public void start() {}

    @Override public void shutdown() {}

    @Override public KeyValue properties() { return properties; }

    //@Override public synchronized void send(Message message) { messageStore.putMessage(message);}
    @Override public void send(Message message) { messageStore.putMessage(message); }
    @Override public void send(Message message, KeyValue properties) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public Promise<Void> sendAsync(Message message) {
        throw new  UnsupportedOperationException("Unsupported");
    }

    @Override public Promise<Void> sendAsync(Message message, KeyValue properties) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public void sendOneway(Message message) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public void sendOneway(Message message, KeyValue properties) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public BatchToPartition createBatchToPartition(String partitionName) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override public BatchToPartition createBatchToPartition(String partitionName, KeyValue properties) {
        throw new UnsupportedOperationException("Unsupported");
    }
}

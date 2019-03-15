# openmessaging-spring 
基于openmessaging-api进行spring-boot融合，做spring-boot项目简便注解使用

<font color=red>目前各MQ厂商对openmssaging规范支持度并不是很好，很多使用方式不支持。</font>

## 依赖

<strong>maven依赖</strong>
```xml
<dependency>
    <groupId>cn.sunline.edsp.midware</groupId>
    <artifactId>openmessaging-spring-boot-starter</artifactId>
    <version>1.0.0-M1</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
    <version>2.0.5</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.0.5.RELEASE</version>
</dependency>
 
<!--相关mq实现集成-->
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-openmessaging</artifactId>
    <version>4.4.0</version>
</dependency>
```

## 消息消费端

<strong><font color=green>推荐模式：</font>

consumer</strong>
```java
@SpringBootApplication
@EnableOMS
public class PushConsumerApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(PushConsumerApplication.class);
    }
}
 
@QueueListener({"TopicTest1"})
public class PushConsumer1 implements MessageListener {
 
    AtomicInteger count = new AtomicInteger(0);
 
    @Override
    public void onReceived(Message message, Context context) {
        System.out.println(new String(message.getBody(byte[].class)) + "," + count.incrementAndGet());
        context.ack();
    }
}
```

<strong>yaml配置

consumer yaml配置</strong>
```xml
oms:
  url: oms:rocketmq://127.0.0.1:9876/default
  keyValue:
    CONSUMER_ID: TopicTestGroup #consumer group
```

## 消息生产者

<strong>producer</strong>
```java
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
```

<strong>yaml配置

producer yaml配置</strong>
```xml
oms:
  url: oms:rocketmq://127.0.0.1:9876/default
```
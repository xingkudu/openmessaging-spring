package com.hugo.midware.openmessaging.autoconfigure;

import com.hugo.midware.openmessaging.impl.ConsumerAdapter;
import com.hugo.midware.openmessaging.impl.ProducerAdapter;
import com.hugo.midware.openmessaging.Consumer;
import com.hugo.midware.openmessaging.Producer;
import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.ResourceManager;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.consumer.PushConsumer;
import io.openmessaging.consumer.StreamingConsumer;
import io.openmessaging.internal.DefaultKeyValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OpenMessagingProperties.class)
public class OpenMessagingAutoConfiguration {

    @ConditionalOnMissingBean(MessagingAccessPoint.class)
    @ConditionalOnProperty(prefix = "oms", value = "url")
    @Bean(initMethod = "startup", destroyMethod = "shutdown")
    public MessagingAccessPoint getMessagingAccessPoint(OpenMessagingProperties properties){
        if(properties.getKeyValue() != null && properties.getKeyValue().size() > 0){
            KeyValue keyValue = new DefaultKeyValue();
            for(Map.Entry<String, String> entry : properties.getKeyValue().entrySet()){
                keyValue.put(entry.getKey(), entry.getValue());
            }
            return OMS.getMessagingAccessPoint(properties.getUrl(), keyValue);
        }
        return OMS.getMessagingAccessPoint(properties.getUrl());
    }

    @Lazy
    @ConditionalOnBean(MessagingAccessPoint.class)
    @ConditionalOnMissingBean(Producer.class)
    @Bean(initMethod = "startup", destroyMethod = "shutdown")
    public Producer getProducer(MessagingAccessPoint messagingAccessPoint){
        return new ProducerAdapter(messagingAccessPoint.createProducer());
    }

    @Lazy
    @ConditionalOnBean(MessagingAccessPoint.class)
    @ConditionalOnMissingBean(Consumer.class)
    @Bean(initMethod = "startup", destroyMethod = "shutdown")
    public Consumer getPullConsumer(MessagingAccessPoint messagingAccessPoint){
        PullConsumer pullConsumer = messagingAccessPoint.createPullConsumer();
        return new ConsumerAdapter(pullConsumer);
    }

    @Lazy
    @ConditionalOnBean(MessagingAccessPoint.class)
    @ConditionalOnMissingBean(PushConsumer.class)
    @Bean(initMethod = "startup", destroyMethod = "shutdown")
    public PushConsumer getPushConsumer(MessagingAccessPoint messagingAccessPoint){
        return messagingAccessPoint.createPushConsumer();
    }

    @Lazy
    @ConditionalOnBean(MessagingAccessPoint.class)
    @ConditionalOnMissingBean(StreamingConsumer.class)
    @Bean(initMethod = "startup", destroyMethod = "shutdown")
    public StreamingConsumer getStreamingConsumer(MessagingAccessPoint messagingAccessPoint){
        return messagingAccessPoint.createStreamingConsumer();
    }

    @Lazy
    @ConditionalOnBean(MessagingAccessPoint.class)
    @ConditionalOnMissingBean(ResourceManager.class)
    public ResourceManager getResourceManager(MessagingAccessPoint messagingAccessPoint){
        return messagingAccessPoint.resourceManager();
    }

}

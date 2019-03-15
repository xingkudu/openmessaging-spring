package com.hugo.midware.openmessaging.processor;

import com.hugo.midware.openmessaging.annotation.QueueListener;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.PushConsumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringValueResolver;
/**
 * @ClassName QueueListenerAnnotationBeanPostProcessor
 * @Description TODO
 * @Author hugo
 * @Date 2019-02-26 15:59
 * @Version 1.0
 **/
public class QueueListenerAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware, EmbeddedValueResolverAware {

    private BeanFactory beanFactory;

    private StringValueResolver stringValueResolver;



    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        if (!clazz.isAnnotationPresent(QueueListener.class) || clazz.isAnnotation() || clazz.isAnonymousClass() || clazz.isArray() || clazz.isEnum() || clazz.isInterface() || !MessageListener.class.isAssignableFrom(clazz)) {
            return true;
        }
        QueueListener queueListener = AnnotationUtils.getAnnotation(clazz, QueueListener.class);
        memberValues(queueListener);
        PushConsumer pushConsumer = beanFactory.getBean(PushConsumer.class);
        for(String queue : queueListener.value()){
            pushConsumer.attachQueue(queue, (MessageListener)bean);
        }
        return true;
    }

    private boolean memberValues(QueueListener queue){
        //通过占位符获取queue
//        try {
//            InvocationHandler handler = Proxy.getInvocationHandler(queue);
//            Field hField = handler.getClass().getDeclaredField("memberValues");
//            //获得权限
//            hField.setAccessible(true);
//            Map memberValues = (Map) hField.get(handler);
//            //从配置文件中获取值，并更改注解Queue中属性的值
//            memberValues.put("value", stringValueResolver.resolveStringValue(queue.value()));
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            if(logger.isWarnEnabled()) {
//                logger.warn("no such memberValues field");
//            }
//        }
        return true;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        Assert.notNull(beanFactory, "BeanFactory must not be null");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }
}

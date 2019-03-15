package com.hugo.midware.openmessaging.annotation;

import com.hugo.midware.openmessaging.autoconfigure.OpenMessagingAutoConfiguration;
import com.hugo.midware.openmessaging.processor.OMSRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OMSRegistrar.class, OpenMessagingAutoConfiguration.class})
public @interface EnableOMS {
}

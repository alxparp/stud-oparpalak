package com.eisgroup.layered;

import com.eisgroup.layered.impl.ValidatorImpl;
import com.eisgroup.layered.proxy.AnnotationProxy;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.ParseException;

public class Start {

    private static Logger LOGGER = Logger.getLogger(ValidatorImpl.class);

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {

        LOGGER.info("Start application");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:*-context.xml");

        AnnotationProxy annotationProxy = (AnnotationProxy) applicationContext.getBean("annotationProxy");
        Validator validator = (Validator) annotationProxy.getProxy();
        String result = validator.getDate("21.02.2018");
        System.out.println(result);

        LOGGER.info("Close application");
    }
}

package com.eisgroup.layered.proxy;

import com.eisgroup.layered.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.eisgroup.layered/cache-test-context.xml")
public class AnnotationProxyTest {

    @Autowired
    @Mock
    private Fibonacci fibonacci;

    @Autowired
    private AnnotationProxy annotationProxy;

    @Autowired
    private Cache cache;

    private final int INDEX = 6;
    private final int RESULT = 8;

    @Test
    public void checkCacheNull() throws ClassNotFoundException {
        assertNotNull(cache);
    }

    @Test
    public void getProxy() throws ClassNotFoundException, ParseException {

        when(fibonacci.getNumberByIndex(INDEX)).thenReturn(RESULT);
        int dataSimple = fibonacci.getNumberByIndex(INDEX);

        Fibonacci fibonacciProxy = (Fibonacci) annotationProxy.getProxy();
        int dataProxy = fibonacciProxy.getNumberByIndex(INDEX);


        assertEquals(RESULT, dataProxy);
        assertEquals(RESULT, dataSimple);
    }

    @Test
    public void interfaceHasAnnotation() throws ClassNotFoundException {
        Fibonacci fibonacciProxy = (Fibonacci) annotationProxy.getProxy();
        Integer dataProxy = fibonacciProxy.getIndexByNumber(RESULT);

        Integer dataCache = (Integer) cache.get(RESULT);

        assertNotNull(dataCache);
        assertEquals(dataProxy, dataCache);
    }

}

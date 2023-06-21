package com.eisgroup.layered.proxy;

import com.eisgroup.layered.cache.Cache;
import com.eisgroup.layered.annotation.Cacheable;
import org.apache.log4j.Logger;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnnotationProxy {

    private Cache cache;

    private Cacheable cacheable;

    private Object obj;

    public AnnotationProxy() {}

    private Cacheable getCacheable() throws ClassNotFoundException {
        AnnotatedType[] annotatedTypes = obj.getClass().getAnnotatedInterfaces();

        for (AnnotatedType annotatedType : annotatedTypes) {
            Class clazz = Class.forName(annotatedType.getType().getTypeName());
            cacheable = (Cacheable) clazz.getDeclaredAnnotation(Cacheable.class);
            if (cacheable != null)
                break;
        }
        return cacheable;
    }

    public Object getProxy() throws ClassNotFoundException {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object key = args[0];
                    Object value = cache.get(key);

                    if (value == null) {
                        value = method.invoke(obj, args);
                    } else {
                        return value;
                    }

                    if (args.length > 0) {
                        if ((cacheable = getCacheable()) == null) {
                            Method met = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
                            cacheable = met.getAnnotation(Cacheable.class);
                            if (cacheable == null) {
                                cacheable = method.getAnnotation(Cacheable.class);
                            }
                        }

                        if (cacheable != null) {
                            if (cacheable.value() == Cacheable.ATTRIBUTES.NULL_SKIP && value == null) {
                                return value;
                            }
                            cache.put(key, value);
                        }
                    }
                    return value;
                });
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
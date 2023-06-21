package com.eisgroup.layered;

import com.eisgroup.layered.cache.Cache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.eisgroup.layered/cache-it-context.xml")
public class CacheIT {

    @Autowired
    private Cache<String, String> cache;

    private static final String A = "A";

    @Before
    public void before() throws Exception {
        cache.put("a", "A");
        cache.put("b", "B");
        cache.put("c", "C");
    }

    @After
    public void after() throws Exception {
        cache.clear();
    }

    @Test
    public void context() {
        cache.put("a", A);
        String result = cache.get("a");
        assertEquals(result, A);
    }

}

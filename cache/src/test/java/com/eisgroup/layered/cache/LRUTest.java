package com.eisgroup.layered.cache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LRUTest {

    private LRU<String, String> lru;

    private static final String A = "A";

    private static final String B = "B";

    private static final String C = "C";

    private static final String D = "D";

    private static final String E = "E";

    @Before
    public void before() throws Exception {
        lru = new LRU<>(3);
    }

    @After
    public void after() throws Exception {
        lru.clear();
        lru = null;
    }

    private static void assertMiss(LRU lru, String key) {
        assertNull(lru.get(key));
    }

    private static void assertHit(LRU lru, String key, String value) {
        assertThat(lru.get(key), is(value));
    }

    private static void assertSnapshot(LRU<String, String> lru, String... keysAndValues) {
        List<String> actualKeysAndValues = new ArrayList<>();
        for (Map.Entry<String, String> entry : lru.snapshot().entrySet()) {
            actualKeysAndValues.add(entry.getKey());
            actualKeysAndValues.add(entry.getValue());
        }
        Assert.assertEquals(Arrays.asList(keysAndValues), actualKeysAndValues);
    }

    @Test
    public void defaultMemorySize() {
        assertThat(lru.getMaxMemorySize(), is(3));
    }

    @Test
    public void logic() {
        lru.put("a", A);
        assertHit(lru, "a", A);
        lru.put("b", B);
        assertHit(lru, "a", A);
        assertHit(lru, "b", B);
        assertSnapshot(lru, "a", A, "b", B);

        lru.put("c", C);
        assertHit(lru, "a", A);
        assertHit(lru, "b", B);
        assertHit(lru, "c", C);
        assertSnapshot(lru, "a", A, "b", B, "c", C);

        lru.put("d", D);
        assertMiss(lru, "a");
        assertHit(lru, "b", B);
        assertHit(lru, "c", C);
        assertHit(lru, "d", D);
        assertHit(lru, "b", B);
        assertHit(lru, "c", C);
        assertSnapshot(lru, "d", D, "b", B, "c", C);

        lru.put("e", E);
        assertMiss(lru, "d");
        assertMiss(lru, "a");
        assertHit(lru, "e", E);
        assertHit(lru, "b", B);
        assertHit(lru, "c", C);
        assertSnapshot(lru, "e", E, "b", B, "c", C);
    }

    @Test
    public void constructorDoesNotAllowZeroCacheSize() {
        try {
            new LRU(0);
            fail();
        } catch (IllegalArgumentException expected) {}
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPutNullKey() {
        lru.put(null, "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPutNullValue() {
        lru.put("a", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotRemoveNullKey() {
        lru.remove(null);
    }

    @Test
    public void putCauseEviction() {
        lru.put("a", A);
        lru.put("b", B);
        lru.put("c", C);
        lru.put("b", D);
        assertSnapshot(lru, "a", A, "c", C, "b", D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWithNullKey() {
        lru.get(null);
    }

    @Test
    public void clear() {
        lru.put("a", "a");
        lru.put("b", "b");
        lru.put("c", "c");
        lru.clear();
        assertThat(lru.snapshot().size(), is(0));
    }

}

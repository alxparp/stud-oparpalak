package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class ProducerTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Producer.class, Entity.class, Table.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(
                Producer.class, "id", Id.class, Column.class, GeneratedValue.class, SequenceGenerator.class);
        AssertAnnotations.assertField(Producer.class, "name", Column.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Producer.class, "getName");
        AssertAnnotations.assertMethod(Producer.class, "getId");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Producer.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Producer.class, Table.class);
        Assert.assertEquals("PRODUCERS", t.name());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Producer.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Producer.getAll", inner.name());
        Assert.assertEquals("SELECT p FROM Producer p", inner.query());
    }

    @Test
    public void id() {
        Column c = ReflectTool.getFieldAnnotation(
                Producer.class, "id", Column.class);

        GeneratedValue g = ReflectTool.getFieldAnnotation(
                Producer.class, "id", GeneratedValue.class);

        SequenceGenerator s = ReflectTool.getFieldAnnotation(
                Producer.class, "id", SequenceGenerator.class);

        Assert.assertEquals("ID", c.name());

        Assert.assertEquals("producers_seq", g.generator());
        Assert.assertEquals(GenerationType.SEQUENCE, g.strategy());

        Assert.assertEquals("producers_seq", s.name());
        Assert.assertEquals("PRODUCERS_SEQ", s.sequenceName());


    }

    @Test
    public void name() {
        Column c = ReflectTool.getFieldAnnotation(
                Producer.class, "name", Column.class);
        Assert.assertEquals("NAME", c.name());
    }
}

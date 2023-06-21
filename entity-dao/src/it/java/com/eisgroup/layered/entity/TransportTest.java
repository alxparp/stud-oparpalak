package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class TransportTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Transport.class, Entity.class, Table.class, Inheritance.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(
                Transport.class, "id", Id.class, Column.class, GeneratedValue.class, SequenceGenerator.class);
        AssertAnnotations.assertField(Transport.class, "name", Column.class);
        AssertAnnotations.assertField(Transport.class, "price", Column.class);
        AssertAnnotations.assertField(Transport.class, "availability", Column.class);
        AssertAnnotations.assertField(Transport.class, "producer", ManyToOne.class);
        AssertAnnotations.assertField(Transport.class, "sensors", ManyToMany.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Transport.class, "getId");
        AssertAnnotations.assertMethod(Transport.class, "getName");
        AssertAnnotations.assertMethod(Transport.class, "getPrice");
        AssertAnnotations.assertMethod(Transport.class, "isAvailability");
        AssertAnnotations.assertMethod(Transport.class, "getProducer");
        AssertAnnotations.assertMethod(Transport.class, "getSensors");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Transport.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Transport.class, Table.class);
        Assert.assertEquals("TRANSPORTS", t.name());
    }

    @Test
    public void inheritance() {
        Inheritance i = ReflectTool.getClassAnnotation(Transport.class, Inheritance.class);
        Assert.assertEquals(InheritanceType.JOINED, i.strategy());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Transport.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Transport.getAll", inner.name());
        Assert.assertEquals("SELECT t FROM Transport t", inner.query());
    }

    @Test
    public void id() {
        Column c = ReflectTool.getFieldAnnotation(
                Transport.class, "id", Column.class);

        GeneratedValue g = ReflectTool.getFieldAnnotation(
                Transport.class, "id", GeneratedValue.class);

        SequenceGenerator s = ReflectTool.getFieldAnnotation(
                Transport.class, "id", SequenceGenerator.class);

        Assert.assertEquals("ID", c.name());

        Assert.assertEquals("transports_seq", g.generator());
        Assert.assertEquals(GenerationType.SEQUENCE, g.strategy());

        Assert.assertEquals("transports_seq", s.name());
        Assert.assertEquals("TRANSPORTS_SEQ", s.sequenceName());
    }

    @Test
    public void name() {
        Column c = ReflectTool.getFieldAnnotation(
                Transport.class, "name", Column.class);
        Assert.assertEquals("NAME", c.name());
    }

    @Test
    public void price() {
        Column c = ReflectTool.getFieldAnnotation(
                Transport.class, "price", Column.class);
        Assert.assertEquals("PRICE", c.name());
    }

    @Test
    public void availability() {
        Column c = ReflectTool.getFieldAnnotation(
                Transport.class, "availability", Column.class);
        Assert.assertEquals("AVAILABILITY", c.name());
    }

    @Test
    public void producer() {
        ManyToOne m = ReflectTool.getFieldAnnotation(
                Transport.class, "producer", ManyToOne.class);
        Assert.assertEquals(CascadeType.ALL, m.cascade()[0]);
    }

    @Test
    public void sensors() {
        ManyToMany m = ReflectTool.getFieldAnnotation(
                Transport.class, "sensors", ManyToMany.class);
        Assert.assertEquals("transports", m.mappedBy());
    }
}

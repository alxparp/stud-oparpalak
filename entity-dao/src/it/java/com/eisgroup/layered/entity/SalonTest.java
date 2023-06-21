package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class SalonTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Salon.class, Entity.class, Table.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(
                Salon.class, "id", Id.class, Column.class, GeneratedValue.class, SequenceGenerator.class);
        AssertAnnotations.assertField(Salon.class, "name", Column.class);
        AssertAnnotations.assertField(Salon.class, "transports", OneToMany.class, JoinColumn.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Salon.class, "getId");
        AssertAnnotations.assertMethod(Salon.class, "getName");
        AssertAnnotations.assertMethod(Salon.class, "getTransports");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Salon.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Salon.class, Table.class);
        Assert.assertEquals("SALONS", t.name());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Salon.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Salon.getAll", inner.name());
        Assert.assertEquals("SELECT s FROM Salon s", inner.query());
    }

    @Test
    public void id() {
        Column c = ReflectTool.getFieldAnnotation(
                Salon.class, "id", Column.class);

        GeneratedValue g = ReflectTool.getFieldAnnotation(
                Salon.class, "id", GeneratedValue.class);

        SequenceGenerator s = ReflectTool.getFieldAnnotation(
                Salon.class, "id", SequenceGenerator.class);

        Assert.assertEquals("ID", c.name());

        Assert.assertEquals("salons_seq", g.generator());
        Assert.assertEquals(GenerationType.SEQUENCE, g.strategy());

        Assert.assertEquals("salons_seq", s.name());
        Assert.assertEquals("SALONS_SEQ", s.sequenceName());
    }

    @Test
    public void name() {
        Column c = ReflectTool.getFieldAnnotation(
                Salon.class, "name", Column.class);
        Assert.assertEquals("NAME", c.name());
    }

    @Test
    public void transports() {
        OneToMany o = ReflectTool.getFieldAnnotation(
                Salon.class, "transports", OneToMany.class);

        JoinColumn j = ReflectTool.getFieldAnnotation(
                Salon.class, "transports", JoinColumn.class);

        Assert.assertEquals(CascadeType.ALL, o.cascade()[0]);

        Assert.assertEquals("transport_id", j.name());
    }
}

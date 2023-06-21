package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class BusTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Bus.class, Entity.class, Table.class, PrimaryKeyJoinColumn.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Bus.class, "seatsNumber", Column.class);
        AssertAnnotations.assertField(
                Bus.class, "transport", OneToOne.class, JoinColumn.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Bus.class, "getSeatsNumber");
        AssertAnnotations.assertMethod(Bus.class, "getTransport");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Bus.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Bus.class, Table.class);
        Assert.assertEquals("BUSES", t.name());
    }

    @Test
    public void primaryKeyJoinColumn() {
        PrimaryKeyJoinColumn p = ReflectTool.getClassAnnotation(Bus.class, PrimaryKeyJoinColumn.class);
        Assert.assertEquals("ID", p.referencedColumnName());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Bus.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Bus.getAll", inner.name());
        Assert.assertEquals("SELECT b FROM Bus b", inner.query());
    }

    @Test
    public void seatsNumber() {
        Column c = ReflectTool.getFieldAnnotation(
                Bus.class, "seatsNumber", Column.class);
        Assert.assertEquals("SEATS_NUMBER", c.name());
    }

    @Test
    public void transport() {
        JoinColumn joinColumn = ReflectTool.getFieldAnnotation(
                Bus.class, "transport", JoinColumn.class);
        Assert.assertEquals("ID", joinColumn.name());
    }
}

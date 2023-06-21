package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class CargoTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Cargo.class, Entity.class, Table.class, PrimaryKeyJoinColumn.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Cargo.class, "bodyType", Column.class);
        AssertAnnotations.assertField(
                Cargo.class, "transport", OneToOne.class, JoinColumn.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Cargo.class, "getBodyType");
        AssertAnnotations.assertMethod(Cargo.class, "getTransport");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Cargo.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Cargo.class, Table.class);
        Assert.assertEquals("CARGO", t.name());
    }

    @Test
    public void primaryKeyJoinColumn() {
        PrimaryKeyJoinColumn p = ReflectTool.getClassAnnotation(Cargo.class, PrimaryKeyJoinColumn.class);
        Assert.assertEquals("ID", p.referencedColumnName());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Cargo.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Cargo.getAll", inner.name());
        Assert.assertEquals("SELECT c FROM Cargo c", inner.query());
    }

    @Test
    public void mileageKm() {
        Column c = ReflectTool.getFieldAnnotation(
                Cargo.class, "bodyType", Column.class);
        Assert.assertEquals("BODY_TYPE", c.name());
    }

    @Test
    public void transport() {
        JoinColumn joinColumn = ReflectTool.getFieldAnnotation(
                Car.class, "transport", JoinColumn.class);
        Assert.assertEquals("ID", joinColumn.name());
    }
}

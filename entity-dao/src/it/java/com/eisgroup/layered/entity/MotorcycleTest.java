package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class MotorcycleTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Cargo.class, Entity.class, Table.class, PrimaryKeyJoinColumn.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Motorcycle.class, "issueYear", Column.class);
        AssertAnnotations.assertField(
                Cargo.class, "transport", OneToOne.class, JoinColumn.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Motorcycle.class, "getIssueYear");
        AssertAnnotations.assertMethod(Motorcycle.class, "getTransport");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Motorcycle.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Motorcycle.class, Table.class);
        Assert.assertEquals("MOTORCYCLES", t.name());
    }

    @Test
    public void primaryKeyJoinColumn() {
        PrimaryKeyJoinColumn p = ReflectTool.getClassAnnotation(Motorcycle.class, PrimaryKeyJoinColumn.class);
        Assert.assertEquals("ID", p.referencedColumnName());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Motorcycle.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Motorcycle.getAll", inner.name());
        Assert.assertEquals("SELECT m FROM Motorcycle m", inner.query());
    }

    @Test
    public void issueYear() {
        Column c = ReflectTool.getFieldAnnotation(
                Motorcycle.class, "issueYear", Column.class);
        Assert.assertEquals("ISSUE_YEAR", c.name());
    }

    @Test
    public void transport() {
        JoinColumn joinColumn = ReflectTool.getFieldAnnotation(
                Motorcycle.class, "transport", JoinColumn.class);
        Assert.assertEquals("ID", joinColumn.name());
    }
}

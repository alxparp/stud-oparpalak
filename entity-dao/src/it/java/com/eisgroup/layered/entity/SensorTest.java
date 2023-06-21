package com.eisgroup.layered.entity;

import com.eisgroup.layered.utils.AssertAnnotations;
import com.eisgroup.layered.utils.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

public class SensorTest {
    @Test
    public void typeAnnotations() {
        AssertAnnotations.assertType(
                Sensor.class, Entity.class, Table.class, NamedQueries.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(
                Sensor.class, "id", Id.class, Column.class, GeneratedValue.class, SequenceGenerator.class);
        AssertAnnotations.assertField(Sensor.class, "name", Column.class);
        AssertAnnotations.assertField(Sensor.class, "transports", ManyToMany.class, JoinTable.class);
    }

    @Test
    public void methodAnnotations() {
        AssertAnnotations.assertMethod(Sensor.class, "getId");
        AssertAnnotations.assertMethod(Sensor.class, "getName");
        AssertAnnotations.assertMethod(Sensor.class, "getTransports");
    }

    @Test
    public void entity() {
        Entity a = ReflectTool.getClassAnnotation(Sensor.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = ReflectTool.getClassAnnotation(Sensor.class, Table.class);
        Assert.assertEquals("SENSORS", t.name());
    }

    @Test
    public void namedQueries() {
        NamedQueries n = ReflectTool.getClassAnnotation(Sensor.class, NamedQueries.class);
        NamedQuery inner = n.value()[0];

        Assert.assertEquals("Sensor.getAll", inner.name());
        Assert.assertEquals("SELECT s FROM Sensor s", inner.query());
    }

    @Test
    public void id() {
        Column c = ReflectTool.getFieldAnnotation(
                Sensor.class, "id", Column.class);

        GeneratedValue g = ReflectTool.getFieldAnnotation(
                Sensor.class, "id", GeneratedValue.class);

        SequenceGenerator s = ReflectTool.getFieldAnnotation(
                Sensor.class, "id", SequenceGenerator.class);

        Assert.assertEquals("ID", c.name());

        Assert.assertEquals("sensors_seq", g.generator());
        Assert.assertEquals(GenerationType.SEQUENCE, g.strategy());

        Assert.assertEquals("sensors_seq", s.name());
        Assert.assertEquals("SENSORS_SEQ", s.sequenceName());
    }

    @Test
    public void name() {
        Column c = ReflectTool.getFieldAnnotation(
                Sensor.class, "name", Column.class);
        Assert.assertEquals("NAME", c.name());
    }

    @Test
    public void transports() {
        ManyToMany m = ReflectTool.getFieldAnnotation(
                Sensor.class, "transports", ManyToMany.class);

        JoinTable j = ReflectTool.getFieldAnnotation(
                Sensor.class, "transports", JoinTable.class);
        JoinColumn joinColumn[] = j.joinColumns();
        JoinColumn inverseJoinColumn[] = j.inverseJoinColumns();

        Assert.assertEquals(CascadeType.PERSIST, m.cascade()[0]);
        Assert.assertEquals(CascadeType.MERGE, m.cascade()[1]);

        Assert.assertEquals("SENSOR_TRANSPORT", j.name());
        Assert.assertEquals("SENSOR_ID", joinColumn[0].name());
        Assert.assertEquals("TRANSPORT_ID", inverseJoinColumn[0].name());
    }
}

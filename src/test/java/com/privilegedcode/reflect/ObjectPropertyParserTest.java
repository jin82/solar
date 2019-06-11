package com.privilegedcode.reflect;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jin on 2019-06-06.
 */
public class ObjectPropertyParserTest {

    private class TestDemo{
        @Getter @Setter
        private String field;
        @Setter
        private Integer nonGetter;
        @Getter
        private Integer nonSetter;

    }

    private ObjectPropertyParser<TestDemo> parser = new ObjectPropertyParser<>(TestDemo.class);

    @Test
    public void getPropertyTest() {
        ObjectProperty field = parser.getProperty("field");
        Assert.assertNotNull(field);
        Assert.assertEquals(field.getName(), "field");
        Assert.assertTrue(field.hasGetter());
        Assert.assertTrue(field.hasSetter());
    }

    @Test
    public void setValueTest() {
        TestDemo demo = new TestDemo();
        int result = parser.putProperty(demo, "nonGetter", -1);
        Assert.assertEquals(1, result);
        Assert.assertEquals(demo.nonGetter, Integer.valueOf(-1));

        int resultField = parser.putProperty(demo, "nonSetter", 2);
        Assert.assertEquals(2, resultField);
        Assert.assertEquals(Integer.valueOf(2), demo.getNonSetter());

        int resultNotExists = parser.putProperty(demo, "nontExists", 0);
        Assert.assertEquals(0,resultNotExists);
    }

}
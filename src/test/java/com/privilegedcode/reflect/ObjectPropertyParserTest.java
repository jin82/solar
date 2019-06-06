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
    public void getProperty() {
        ObjectProperty field = parser.getProperty("field");
        Assert.assertNotNull(field);
        Assert.assertEquals(field.getName(), "field");
        Assert.assertTrue(field.hasGetter());
        Assert.assertTrue(field.hasSetter());
    }

}
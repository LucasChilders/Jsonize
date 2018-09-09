package com.lucaschilders;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JsonizeTest {

    @Test
    public void createWithDifferentTypes() {
        Jsonize json = new Jsonize();
        json.add("Test", "Test");
        json.add(1, 9);
        json.add(49, 493.92);
        json.add("String", 23);

        assertTrue(true);
    }

    @Test
    public void unsetNonExistentValue() {
        Jsonize json = new Jsonize();
        json.add(1, 2);

        assertFalse(json.remove(3));
    }

    @Test
    public void unsetExistentValue() {
        Jsonize json = new Jsonize();
        json.add(1, 2);

        assertTrue(json.remove(1));
    }

    @Test
    public void updateAddNewValue() {
        Jsonize json = new Jsonize();
        json.update(1, 2);

        assertTrue(json.exists(1));
    }

    @Test
    public void updateValue() {
        Jsonize json = new Jsonize();
        json.add(1, 2);
        assertEquals(json.value(1), 2);

        json.update(1, 3);
        assertEquals(json.value(1), 3);
    }

    @Test
    public void verifyGetValue() {
        Jsonize json = new Jsonize();
        json.add(1, 2);

        assertEquals(json.value(1), 2);
    }

    @Test
    public void verifyExistedObjectExists() {
        Jsonize json = new Jsonize();
        json.add(1, 2);

        assertTrue(json.getMap().containsKey(1));
        assertTrue(json.exists(1));
    }

    @Test
    public void getIsObjectStatus() {
        Jsonize json = new Jsonize(true);
        assertTrue(json.getIsObject());
    }

    @Test
    public void setIsObjectStatus() {
        Jsonize json = new Jsonize();
        assertFalse(json.getIsObject());

        json.setIsObject(true);
        assertTrue(json.getIsObject());
    }

    @Test
    public void verifyQuickWorks() {
        String response = Jsonize.quick(1, 2, false);
        assertEquals(response, "{\"1\": \"2\"}");
    }

    @Test
    public void verifyOutputFormatting() {
        Jsonize json = new Jsonize();
        json.add(1, 2);
        assertEquals(json.toString(), "{\"1\": \"2\"}");

        json.add(2, 3);
        assertEquals(json.toString(), "{\"1\": \"2\",\"2\": \"3\"}");
    }

    @Test
    public void verifyOutputFormattingWithObject() {
        Jsonize json = new Jsonize(true);
        json.add(1, 2);
        assertEquals(json.toString(), "[{\"1\": \"2\"}]");

        json.add(2, 3);
        assertEquals(json.toString(), "[{\"1\": \"2\",\"2\": \"3\"}]");
    }
}

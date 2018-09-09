package com.lucaschilders;

/**
 * @author  Lucas Childers
 * @version 0.2.0
 * @date    9/9/2018
 *
 * Simple way to aggregate data into a simple one-object JSON response that should have
 * just used methods from the HashMap.java class. Clean your values before adding key /
 * value pairs, you don't want to end up with illegal JSON tokens.
 *
 * isObject tells the toString output to append [ and ] to the beginning and end of JSON
 * string.
 *
 *
 * Methods
 *
 * set objects
 * update objects
 * remove objects
 * check if objects exist
 * get value at key
 * get entire map
 *
 * Bonus: Statically call quick() for a one item response.
 *
 */

import java.util.HashMap;
import java.util.Map;

public class Jsonize {
    private Map<Object, Object> map;
    private boolean isObject;

    Jsonize() {
        this(false);
    }

    Jsonize(boolean isObject) {
        this.isObject = isObject;
        map = new HashMap<>(0);
    }

    Jsonize(Object key, Object value, boolean isObject) {
        this(isObject);
        this.add(key, value);
    }

    /**
     * Some JSON parsers are happy if the JSON object starts and ends with [ and ]
     * respectively.
     *
     * @param isObject tells the toString method to append [ and ] to the response
     */
    public void setIsObject(boolean isObject) {
        this.isObject = isObject;
    }

    /**
     * Returns the value of isObject.
     *
     * @return isObject
     */
    public boolean getIsObject() {
        return this.isObject;
    }

    /**
     * Append key and value to list of items. Accepts any types (hopefully) to get
     * String value of.
     *
     * @param key
     * @param value
     */
    public void add(Object key, Object value) {
        map.put(key, value);
    }

    /**
     * Forcefully add / update existing value in key.
     *
     * @param key
     * @param newValue
     * @return status
     */
    public void update(Object key, Object newValue) {
        map.put(key, newValue);
    }

    /**
     * Unset a value from the list of items.
     *
     * @param key
     * @return status
     */
    public boolean remove(Object key) {
        if (!map.containsKey(key)) {
            return false;
        }

        map.remove(key);
        return true;
    }

    /**
     * Check if value already exists.
     *
     * @param key
     * @return status
     */
    public boolean exists(Object key) {
        return map.containsKey(key);
    }

    /**
     * Get value in list of items.
     *
     * @param key
     * @return Value at key, or null if DNE
     */
    public Object value(Object key) {
        return map.get(key);
    }

    /**
     * You can get the map, buy you can't set the map :)
     * Useful if you want to parse the map yourself.
     *
     * @return The map that Jsonize is using.
     */
    public Map<Object, Object> getMap() {
        return this.map;
    }

    /**
     * Used by quick() and toString() to parse map into JSON
     *
     * @return JSON response
     */
    private String parse() {
        String result = "";

        if (this.isObject) {
            result += "[";
        }

        result += "{";

        for (Object key : map.keySet()) {
            result += String.format("\"%s\": \"%s\",", String.valueOf(key),
                    map.get(key));
        }

        result = result.substring(0, result.length() - 1);
        result += "}";

        if (this.isObject) {
            result += "]";
        }

        return result;
    }

    /**
     * For one item responses such as errors.
     *
     * @param key
     * @param value
     * @return Single item JSON response.
     */
    public static String quick(Object key, Object value, boolean isObject) {
        return new Jsonize(key, value, isObject).parse();
    }

    /**
     * Will return the Jsonize object as a String for simplicity.
     *
     * @return JSON formatted String of items from the map.
     */
    @Override
    public String toString() {
        return parse();
    }
}

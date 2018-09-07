package com.lucaschilders;

/**
 * @author  Lucas Childers
 * @version 0.1.0
 * @date    9/3/2018
 *
 * Simple way to aggregate data into a simple one-object JSON response.
 *
 * Example:
 * Jsonize json = new Jsonize();
 * json.add("message", "success!");
 * json.add("code", messageCode);
 * System.out.println(json);
 */

import java.util.HashMap;
import java.util.Map;

public class Jsonize {
    Map<String, String> map;

    Jsonize() {
        map = new HashMap<>(0);
    }

    /**
     * Append key and value to list of items. Accepts any types (hopefully) to get String value of.
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        map.put(key, String.valueOf(value));
    }

    /**
     * For one item responses such as errors.
     *
     * @param key
     * @param value
     * @return Single item JSON response.
     */
    public static String quick(String key, Object value) {
        String result = "{";
        result += String.valueOf(value);
        result += String.format("\"%s\": \"%s\"", key, value);
        result += "}";
        return result;
    }

    /**
     * Will return the Jsonize object as a String for simplicity.
     *
     * @return JSON formatted String of items from the map.
     */
    @Override
    public String toString() {
        String result = "";
        result += "{";

        for (String key : map.keySet()) {
            result += String.format("\"%s\": \"%s\",", key, map.get(key));
        }

        result = result.substring(0, result.length() - 1);
        result += "}";

        return result;
    }
}

# Jsonize
Simple (but way overly complicated) way aggregate data into a simple one-object JSON response that should have just used methods from the HashMap.java class. Clean your values before adding key value pairs, you don't want to end up with illegal JSON tokens.

* isObject tells the toString output to append [ and ] to the beginning and end of JSON string.

## Usage 
You should just be able to throw the JAR file from a tagged release as an included library in the IDE of your choice. 

See: https://github.com/LucasChilders/Jsonize/releases

## Methods
* set objects
* update objects
* remove objects
* check if objects exist
* get value at key
* get entire map

### Bonus: Statically call quick() for a one item response.

## Example Use

```java
Jsonize json = new Jsonize(); // Defaults to isObject = false, will not add [ and ] to output.
json.add("message", "There has been an error");
json.add("code", 404);

return json.toString(); // {"message": "There has been an error","code": "404"}
```
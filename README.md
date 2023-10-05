# Java Result

Java Result is a library that implements Result in Java. Results is alternative to Exceptions, with no hidden flows. Only works with Java 21+

## Usage

Result is a Generic Interface that have two implementations, Ok and Err, both have same Generic parameters.

```java
public sealed interface Result<O, E> permits Ok, Err {}
public record Ok<O, E>(O o) implements Result<O, E> {}
public record Err<O, E>(E err) implements Result<O, E> {
```

Create a Ok result

```java
public Result<String, String> okResult() {
    return Result.ok("this is a ok result");
}
```

Create a Err Result

```java
public Result<String, String> errResult() {
    return Result.err("this is a err result");
}
```

Test example
```java
void matchWithWhen() {
    record Person(int age){}
    final Result<Person, String> ok = Result.ok(new Person(10));
    switch (ok) {
        case Ok(var p) when p.age() > 18 -> assertTrue(false);
        case Ok(var p) -> assertTrue(true);
        default -> assertTrue(false);
    }
}
```
Result have, method like
- map, method that map Ok result, receive a Function and result another `Result<O, E>`.
- mapErr, method that map Err result, receive a Function and result another `Result<O, E>`.
- unwrap get Ok value, throws a exception if the value is a error.
- okOrDefault get Ok Value, if is a Err return a default value.

Add to project:
```xml
<dependency>
  <groupId>dev.pgjbz</groupId>
  <artifactId>java-result</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

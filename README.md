# Java Result

Java Result is a library that implements Result in Java. Results is alternative to Exceptions, with no hidden flows. NO MORE EXCEPTIONS

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

Unwrap an Ok Result
```java
void unwrapOk() {
    record Person(int age){}
    final Result<Person, String> ok = Result.ok(new Person(10));
    final Person person = ok.unwrap();
}
```

Unwrap an Err Result:

```java
void unwrapErr() {
    record Person(int age){}
    final Result<Person, String> err = Result.err("error");
    final Person person = err.unwrap(); //throws an error
}
```

Switch a Result:

```java
void matchResult() {
    record Person(int age){}
    final Result<Person, String> ok = Result.ok(new Person(10));
    switch(ok) {
        case Ok(var person) -> System.out.println("Person age: " + person.age());
        case Err(var err) -> System.err.println(err);
    }
}
```

Map an Ok Result:
```java
void mapAnOkResult() {
    final Result<String, String> ok = Result.ok("2");
    final Integer mapped = ok.map(e -> e.concat("2"))
            .map(Integer::parseInt)
            .unwrap();
}
```

Map an Err result:
```java
void mapAnError() {
    final Result<String, String> err = Result.err("1");
    final Err<Integer> mapped = err
        .map(ok -> ok + "22") //no execute because is an err
        .mapErr(Integer::parseInt);
}
```

Result to Optional:
```java
void resultToOptional() {
    final Result<String, String> ok = Result.ok("ok result");
    final Optional<String> present = ok.optional(); //empty if is err
}
```

Result have, method like
- map, method that map Ok result, receive a Function and result another `Result<O, E>`.
- mapErr, method that map Err result, receive a Function and result another `Result<O, E>`.
- unwrap get Ok value, throws an error if the value is a error.
- okOrDefault get Ok Value, if is a Err return a default value.
- optional return Optional with value if is Ok, and Optional empty if Err
Add to project:

```xml
<dependency>
  <groupId>dev.pgjbz</groupId>
  <artifactId>java-result</artifactId>
  <version>0.1.0</version>
</dependency>
```

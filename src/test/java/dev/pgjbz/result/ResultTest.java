package dev.pgjbz.result;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dev.pgjbz.result.exception.ValueNotCompatibleException;

public class ResultTest {

    @Test
    void shouldWillBeOkValue() {
        final var ok = Result.ok("uau");
        assertDoesNotThrow(() -> ok.unwrap());
    }

    @Test
    void shouldWillBeErrorValue() {
        final var err = Result.err("error");
        assertDoesNotThrow(() -> err.err());
    }

    @Test
    void shouldBeErrorAndThrowsError() {
        final var err = Result.err("error");
        assertThrows(ValueNotCompatibleException.class, () -> err.unwrap());
    }

    @Test
    void shouldMapErrErr() {
        final Result<String, String> err = Result.err("1");
        final var mapped = err
            .map(ok -> ok.concat("123"))
            .mapErr(e -> Integer.parseInt(e)).okOrDefault("def");
        assertEquals("def", mapped);
    }

    @Test
    void shouldMapOk() {
        final Result<String, String> ok = Result.ok("2");
        final var mapped = ok.mapErr(e -> e.concat("2"))
                .map(o -> Integer.parseInt(o))
                .unwrap();
        assertEquals(2, mapped);
    }

    @Test
    void match() {
        final Result<String, String> err = Result.err("err");
        switch(err) {
            case Err<String, String>(var e) -> assertEquals("err", e);
            case Ok<String, String>(var ok) -> assertTrue(false);
            default -> assertTrue(false);
        }        
    }

    @Test
    void matchWithWhen() {
        record Person(int age){}
        final Result<Person, String> ok = Result.ok(new Person(10));
        switch (ok) {
            case Ok(var p) when p.age() > 18 -> assertTrue(false);
            case Ok(var p) -> assertTrue(true);
            default -> assertTrue(false);
        }
    }
}

package dev.pgjbz.result;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        if(err instanceof Err<String, String> e) {
           assertEquals("err", e.err());
        }
    }
}

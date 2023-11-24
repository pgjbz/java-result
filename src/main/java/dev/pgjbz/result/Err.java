package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

import dev.pgjbz.result.exception.NoOkException;

public record Err<O, E>(E err) implements Result<O, E> {

    public Err{
        requireNonNull(err, "null values are not supported");
    }

    @Override
    public O unwrap() {
        throw new NoOkException();
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public boolean isOk() {
        return false;
    }


}

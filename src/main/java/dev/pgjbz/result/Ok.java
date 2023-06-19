package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

import dev.pgjbz.result.exception.ValueNotCompatibleException;

public record Ok<O, E>(O o) implements Result<O, E> {

    public Ok {
        requireNonNull(o, "null values are not supported");
    }

    @Override
    public O unwrap() {
        return o;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public E err() {
        throw new ValueNotCompatibleException("not error value");
    }

}

package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

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

}

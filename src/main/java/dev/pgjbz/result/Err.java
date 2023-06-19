package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

import dev.pgjbz.result.exception.ValueNotCompatibleException;

public  record Err<O, E>(E e) implements Result<O, E> {

    public Err{
        requireNonNull(e, "null values are not supported");
    }

    @Override
    public O unwrap() {
        throw new ValueNotCompatibleException("not ok value");
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    public E err() {
        return this.e;
    }
    
}

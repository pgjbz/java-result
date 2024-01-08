package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

/**
 * Ok class
 * @param <O> Ok value type
 * @param <E> Err value typ
 * @param o Ok value
 * @author Paulo G. J. Bezerra - <a href="mailto:contact@pgjbz.dev">contact@pgjbz.dev</a>
 */
public record Ok<O, E>(O o) implements Result<O, E> {

    /**
     * Create a new Ok value
     * @param o Non null Ok value
     */
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

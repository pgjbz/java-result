package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

/**
 * Error class
 * @author Paulo G. J. Bezerra - <a href="mailto:contact@pgjbz.dev">contact@pgjbz.dev</a>
 * @param <O> Ok value type
 * @param <E> Err value type
 * @param E error value
 */
public record Err<O, E>(E err) implements Result<O, E> {

    /**
     * Create a new error
     * @author Paulo G. J. Bezerra - <a href="mailto:contact@pgjbz.dev">contact@pgjbz.dev</a>
     * @param err Non null Err value
     */
    public Err{
        requireNonNull(err, "null values are not supported");
    }

    @Override
    public O unwrap() {
        throw new NoOkError();
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

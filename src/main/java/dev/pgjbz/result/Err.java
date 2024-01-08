package dev.pgjbz.result;

import static java.util.Objects.requireNonNull;

import dev.pgjbz.result.error.NoOkError;

/**
 * @author Paulo G. J. Bezerra <contact@pgjbz.dev>
 */
public record Err<O, E>(E err) implements Result<O, E> {

    /**
     * Create a new error
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

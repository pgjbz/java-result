package dev.pgjbz.result.error;


/**
 * Error that been throws if no Ok Value
 * @author Paulo G. J. Bezerra <contact@pgjbz.dev>
 */
public class NoOkError extends java.lang.Error {
    public NoOkError() {
        super("value that unwrap is an error");
    }
}
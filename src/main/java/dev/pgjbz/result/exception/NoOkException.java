package dev.pgjbz.result.exception;

public class NoOkException extends RuntimeException {
    public NoOkException() {
        super("value that unwrap is an error");
    }
}
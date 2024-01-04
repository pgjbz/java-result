package dev.pgjbz.result.error;



public class NoOkError extends java.lang.Error {
    public NoOkError() {
        super("value that unwrap is an error");
    }
}
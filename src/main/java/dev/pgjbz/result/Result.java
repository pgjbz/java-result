package dev.pgjbz.result;

import java.util.function.Function;

public sealed interface Result<O, E> permits Err<O, E>, Ok<O, E> {

    O unwrap();
    E err();

    boolean isErr();

    boolean isOk();

    public static <O, E> Err<O, E> err(E error) {
        return new Err<>(error);
    }

    public static <O, E> Ok<O, E> ok(O ok) {
        return new Ok<>(ok);
    }

    default <T> Result<O, T> mapErr(Function<E, T> map) {
        if(this instanceof Err<O, E> e) {
            return Result.err(map.apply(e.err()));
        }
        return Result.ok(unwrap());
    }

    default <T> Result<T, E> map(Function<O, T> map) {
        if(this instanceof Err<O, E> e) {
            return Result.err(e.err());
        }
        return Result.ok(map.apply(unwrap()));
    }

    default O okOrDefault(O def) {
        if (isOk())
            return unwrap();
        return def;
    }

}
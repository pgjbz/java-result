package dev.pgjbz.result;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Paulo G. J. Bezerra <contact@pgbz.dev>
 */
public sealed interface Result<O, E> permits Ok, Err {

    /**
     * 
     * @return return Ok value otherwise throws an error if not is Ok
     */
    O unwrap();

    /**
     * 
     * @return true if is an error
     */
    boolean isErr();

    /** 
     * @return true if an ok
     * */ 
    boolean isOk();

    /**
     * @param <O> Ok value type
     * @param <E> Err value type
     * @param error Create a new Err value with given value
     * @return Err instance with error value
     */
    public static <O, E> Err<O, E> err(E error) {
        return new Err<>(error);
    }

    /**
     * 
     * @param ok Create a new Ok value with given value
     * @return Ok instance with ok value
     */
    public static <O, E> Ok<O, E> ok(O ok) {
        return new Ok<>(ok);
    }

    /**
     * Convert an Result value to Optional, empty optional means value is an error
     * present value is a Ok value
     * @return
     */
    default Optional<O> optional() {
        return isOk() ? Optional.of(unwrap()) : Optional.empty();
    }

    /**
     * Map error values to another error value
     * @param <T> Type of error
     * @param map function that map error to another error
     * @return new error value
     */
    default <T> Result<O, T> mapErr(Function<E, T> map) {
        if(this instanceof Err<O, E> e) {
            return Result.err(map.apply(e.err()));
        }
        return Result.ok(unwrap());
    }

    /**
     * Map Ok value to another Ok value
     * @param <T> Ok value type
     * @param map functions that map Ok to another Ok value
     * @return new Ok value
     */
    default <T> Result<T, E> map(Function<O, T> map) {
        if(this instanceof Err<O, E> e) {
            return Result.err(e.err());
        }
        return Result.ok(map.apply(unwrap()));
    }

    /**
     * Return a ok value or a default value if is an error
     * @param def Default value to return
     * @return Ok value or a default value
     */
    default O okOrDefault(O def) {
        return isOk()? unwrap() :  def;
    }

}
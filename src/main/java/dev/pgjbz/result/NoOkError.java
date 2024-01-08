package dev.pgjbz.result;

/** 
 * Error that be throws if try to unwrap no Ok value
 * @author Paulo G. J. Bezerra - <a href="mailto:contact@pgjbz.dev">contact@pgjbz.dev</a>
*/
public class NoOkError extends Error {
    /**
     * @hiden
     */
    protected NoOkError() {
        super("the value that try to unwrap is an error");
    }
}

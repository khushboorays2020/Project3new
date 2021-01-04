package in.co.sunrays.proj3.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * 
 * @author Decorator
 * @version 1.0
 * @Copyright (c) Decorator
 * 
 */

public class DuplicateRecordException extends Exception {

    /**
     * @param msg
     *            error message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
    }

}

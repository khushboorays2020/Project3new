package in.co.sunrays.proj3.exception;

/**
 * RecordNotFoundException thrown when a record not found occurred
 * 
 * @author Decorator
 * @version 1.0
 * @Copyright (c) Decorator
 * 
 */

public class RecordNotFoundException extends Exception{

    /**
     * @param msg
     *            error message
     */
    public RecordNotFoundException(String msg) {
        super(msg);

    }
}

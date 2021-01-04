package in.co.sunrays.proj3.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author Decorator
 * @version 1.0
 * @Copyright (c) Decorator
 * 
 */

public class DatabaseException extends Exception {

    /**
     * @param msg
     *            : Error message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}

package com.mobtail.exception;

/**
 * Root exception should be throw when theres no need to created a specific one
 */
public class UbanhShareInfoException extends RuntimeException {

    public UbanhShareInfoException() {
        super();
    }

    public UbanhShareInfoException(String message) {
        super(message);
    }

    public UbanhShareInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UbanhShareInfoException(Throwable cause) {
        super(cause);
    }
}

package org.itstep.dao.exception;

/**
 * AppException - for any unhandled Exception
 *
 * @author Tkach Illya
 */
public class AppException extends RuntimeException {

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

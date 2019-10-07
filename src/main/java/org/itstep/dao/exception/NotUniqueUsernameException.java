package org.itstep.dao.exception;

/**
 * NotUniqueUsername Exception
 *
 * @author Tkach Illya
 */
public class NotUniqueUsernameException extends RuntimeException {

    public NotUniqueUsernameException(String message) {
        super(message);
    }
}

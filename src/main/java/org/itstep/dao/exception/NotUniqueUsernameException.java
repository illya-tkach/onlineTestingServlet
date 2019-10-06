package org.itstep.dao.exception;

/**
 * NotUniqueUsername Exception
 *
 * @author Oleksii Morenets
 */
public class NotUniqueUsernameException extends RuntimeException {

    public NotUniqueUsernameException(String message) {
        super(message);
    }
}

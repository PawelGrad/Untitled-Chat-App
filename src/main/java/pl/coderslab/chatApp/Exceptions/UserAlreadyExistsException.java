package pl.coderslab.chatApp.Exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}

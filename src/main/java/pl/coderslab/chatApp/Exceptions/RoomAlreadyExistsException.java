package pl.coderslab.chatApp.Exceptions;

public class RoomAlreadyExistsException extends Exception {
    public RoomAlreadyExistsException(String msg) {
        super(msg);
    }
}

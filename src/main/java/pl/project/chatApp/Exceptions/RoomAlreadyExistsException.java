package pl.project.chatApp.Exceptions;

public class RoomAlreadyExistsException extends Exception {
    public RoomAlreadyExistsException(String msg) {
        super(msg);
    }
}

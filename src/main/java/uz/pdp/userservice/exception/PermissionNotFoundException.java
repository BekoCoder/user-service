package uz.pdp.userservice.exception;

public class PermissionNotFoundException extends RuntimeException{
    public PermissionNotFoundException(String message) {
        super(message);
    }
}

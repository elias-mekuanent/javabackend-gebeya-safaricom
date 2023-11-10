package ChatExceptions;

public class PrivateChatException extends Exception {

    public PrivateChatException(String message) {
        super(message);
    }

    public PrivateChatException(String message, Throwable cause) {
        super(message, cause);
    }
}

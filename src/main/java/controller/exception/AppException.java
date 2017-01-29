package controller.exception;

/**
 * AppException
 * Created by alexey.morenets@gmail.com on 29.01.2017.
 */
public class AppException extends RuntimeException {

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}

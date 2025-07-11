package luis.goes.eval.studentapieval.core.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public final class HttpException extends RuntimeException {
    private final HttpStatus status;

    private HttpException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static HttpException notFound(String message) {
        return new HttpException(message, HttpStatus.NOT_FOUND);
    }

    public static HttpException badRequest(String message) {
        return new HttpException(message, HttpStatus.BAD_REQUEST);
    }

    public static HttpException unauthorized(String message) {
        return new HttpException(message, HttpStatus.UNAUTHORIZED);
    }

    public static HttpException forbidden(String message) {
        return new HttpException(message, HttpStatus.FORBIDDEN);
    }

    public static HttpException conflict(String message) {
        return new HttpException(message, HttpStatus.CONFLICT);
    }

}
package luis.goes.eval.studentapieval.core.exception;


import luis.goes.eval.studentapieval.core.shared.dto.ErrorResponse;
import luis.goes.eval.studentapieval.core.shared.mapper.ConstraintNameMapper;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleApiException(HttpException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {
        String constraintName = null;
        if (ex.getCause() instanceof ConstraintViolationException cve) {
            constraintName = cve.getConstraintName();
        }

        String fieldName = ConstraintNameMapper.getFieldName(constraintName);

        ErrorResponse error = new ErrorResponse("Field '" + fieldName + "' already has a value");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ErrorResponse> handleNullableException(PropertyValueException ex) {
        String property = ex.getPropertyName();
        String entity = ex.getEntityName();

        ErrorResponse error = new ErrorResponse(String.format("The required field '%s' in entity '%s' was not provided.", property, entity));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonInvalid() {
        ErrorResponse error = new ErrorResponse("The JSON sent in the request is invalid. Please check the syntax.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch() {
        ErrorResponse error = new ErrorResponse("Invalid ID: The given value is an invalid ID");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(NoResourceFoundException ex) {
        String formatedMessage = ex.getMessage()
                .replaceFirst("No static resource", "No static resource was found for")
                .replace(".", "");
        ErrorResponse error = new ErrorResponse(formatedMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String method = ex.getMethod();
        String[] supportedMethods = ex.getSupportedMethods();
        String supported = supportedMethods != null ? String.join(", ", supportedMethods) : "none";

        ErrorResponse error = new ErrorResponse(String.format("Method '%s' is not supported for this endpoint. Supported methods: %s.", method, supported));

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException() {
        ErrorResponse error = new ErrorResponse("Internal server error. That's not your fault!");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
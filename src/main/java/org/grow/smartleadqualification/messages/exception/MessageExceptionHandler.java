package org.grow.smartleadqualification.messages.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackages = "org.grow.smartleadqualification.messages")
class MessageExceptionHandler {

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiError> handlePropertyReferenceException(
            HttpServletRequest req,
            PropertyReferenceException e
    ) {
        return error(req, HttpStatus.BAD_REQUEST, "Not valid query params in request",
                List.of("sort: unknown property: '" + e.getPropertyName() + "'"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(
            HttpServletRequest req,
            ConstraintViolationException e
    ) {
        var errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return error(req, HttpStatus.BAD_REQUEST, "Validation errors", errors);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ApiError> handleMessageNotFoundException(
            HttpServletRequest req,
            MessageNotFoundException e
    ) {
        return error(req, HttpStatus.NOT_FOUND, "Message not found!", List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
            HttpServletRequest req,
            MethodArgumentNotValidException e
    ) {
        var errors = e.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList();
        return error(req, HttpStatus.BAD_REQUEST, "Validation errors", errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(
            HttpServletRequest req,
            HttpMessageNotReadableException e
    ) {
        return error(req, HttpStatus.BAD_REQUEST, "Malformed request body",
                List.of("Request body is missing or is not valid JSON"));
    }

    /**
     * Single place that builds the error response, so the HTTP status and the "status" field
     * in the body always come from the same value.
     */
    private ResponseEntity<ApiError> error(
            HttpServletRequest req,
            HttpStatus status,
            String description,
            List<String> errors
    ) {
        ApiError apiError = new ApiError(req.getRequestURI(), status.value(), description, errors);
        return ResponseEntity.status(status).body(apiError);
    }
}

package org.grow.smartleadqualification.messages.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        ApiError apiError = new ApiError(
                req.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                "Not valid query params in request",
                List.of("sort: unknown property: '" + e.getPropertyName() + "'")
        );

        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(
            HttpServletRequest req,
            ConstraintViolationException e
    ) {

        var errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();

        ApiError apiError = new ApiError(
                req.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation errors",
                errors
        );

        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ApiError> handleMessageNotFoundException(
            HttpServletRequest req,
            MessageNotFoundException e
    ) {

        ApiError apiError = new ApiError(
                req.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                "Message not found!",
                List.of()
        );

        return ResponseEntity.badRequest().body(apiError);
    }

}

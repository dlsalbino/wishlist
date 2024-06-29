package com.azusah.wishlist.infrastructure.exception.handler;

import com.azusah.wishlist.domain.exception.EmptyProductListException;
import com.azusah.wishlist.domain.exception.WishlistLimitAchievedException;
import com.azusah.wishlist.domain.exception.WishlistUpdateException;
import com.azusah.wishlist.infrastructure.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WishlistLimitAchievedException.class)
    public ResponseEntity<Object> handleWishlistLimitAchievedExceptions(WishlistLimitAchievedException ex) {
        List<String> details = Collections.singletonList(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Full Wishlist", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WishlistUpdateException.class)
    public ResponseEntity<Object> handleWishlistUpdateExceptions(WishlistUpdateException ex) {
        List<String> details = Collections.singletonList(ex.getCause().getMessage());
        ErrorResponse error = new ErrorResponse("Update Wishlist failed", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyProductListException.class)
    public ResponseEntity<Object> handleEmptyProductListExceptions(EmptyProductListException ex) {
        List<String> details = Collections.singletonList(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Retrieve products failed", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

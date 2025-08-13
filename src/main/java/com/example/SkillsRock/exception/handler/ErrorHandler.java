package com.example.SkillsRock.exception.handler;

import com.example.SkillsRock.exception.exception.BadRequestException;
import com.example.SkillsRock.exception.exception.NotFoundException;
import com.example.SkillsRock.exception.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        return ApiError.create(HttpStatus.NOT_FOUND,
                "The required object was not found.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException e) {
        return ApiError.create(HttpStatus.BAD_REQUEST,
                "Incorrectly made request.",
                e.getMessage());
    }
}

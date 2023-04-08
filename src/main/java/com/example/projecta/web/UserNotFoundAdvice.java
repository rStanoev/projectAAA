package com.example.projecta.web;

import com.example.projecta.domain.dto.model.ProductNotFoundException;
import com.example.projecta.domain.dto.model.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView onProductNotFound(UserNotFoundException unf) {
        ModelAndView modelAndView = new ModelAndView("user-not-found");

        modelAndView.addObject("userId", unf.getuserString());

        return modelAndView;
    }
}

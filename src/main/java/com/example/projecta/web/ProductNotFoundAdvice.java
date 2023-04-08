package com.example.projecta.web;

import com.example.projecta.domain.dto.model.ProductNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ProductNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView onProductNotFound(ProductNotFoundException pnf) {
        ModelAndView modelAndView = new ModelAndView("product-not-found");

        modelAndView.addObject("objectId", pnf.getProdutcId());

        return modelAndView;
    }
}

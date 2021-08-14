//package com.task.Rudolf.task.web.controller.advice;
//
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleCustomGeneralException(Exception ex) {
//        ModelAndView model = new ModelAndView();
//        // add needed model attributes
//        model.addObject("message", ex.getMessage());
//        model.setViewName("generalexception");
//        return model;
//    }
//
//}
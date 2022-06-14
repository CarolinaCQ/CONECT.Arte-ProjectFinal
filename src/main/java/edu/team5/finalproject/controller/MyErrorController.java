package edu.team5.finalproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.team5.finalproject.exception.ExceptionMessages;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request){

        ModelAndView mav = new ModelAndView("error");
        Integer status = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message;

        switch (status){
            case 200:
                message = ExceptionMessages.SUCCESS_STATUS_CODE_200.getName();
                break;
            case 300:
                message = ExceptionMessages.ERROR_STATUS_CODE_300.getName();
                break;
            case 403:
                message = ExceptionMessages.ERROR_STATUS_CODE_403.getName();
                break;
            case 404:
                message = ExceptionMessages.ERROR_STATUS_CODE_404.getName();
                break;
            case 500:
                message = ExceptionMessages.ERROR_STATUS_CODE_500.getName();
                break;
            default:
                message = ExceptionMessages.ERROR_STATUS_CODE_DEFAULT.getName();
        }
        mav.addObject("message",message);
        mav.addObject("status", status);
        return mav;
    }
}

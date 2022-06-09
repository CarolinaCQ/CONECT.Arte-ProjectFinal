package edu.team5.finalproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
            case 100:
                message = "Usted se encuentra en la seccion informativa";
                break;
            case 200:
                message = "Su peticion se ha realizado con exito";
                break;
            case 300:
                message = "La pagina solicitada se puede encontrar en una URI distinta";
            case 403:
                message = "No tiene los permisos para acceder a esta pagina o el servidos se niega a acceder ";
                break;
            case 404:
                message = "La direccion ingresada no existe o podria estar disponible en el futuro";
                break;
            case 500:
                message = "Esta pagina no esta disponible por mantenimiento";
                break;
            default:
                message = "Error inesperado al cargar la pagina";
        }
        mav.addObject("message",message);
        mav.addObject("status", status);
        return mav;
    }
}

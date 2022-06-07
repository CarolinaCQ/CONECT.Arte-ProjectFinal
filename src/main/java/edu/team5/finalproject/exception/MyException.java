package edu.team5.finalproject.exception;

/*
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)*/            
public class MyException extends Exception{
    
    /*
    private Long id;
    private String resource;                   PROBAR LOS BLOQUES COMENTADOS DESPUES...
    private String field;    

    public MyException(Long id, String resource, String field) {
        super(String.format("%s no encontrado con: %s : '%s'", resource, field, id));
        this.id = id;
        this.resource = resource;
        this.field = field;
    }
    */

    public MyException(String exception){
        super(exception);
    }

}

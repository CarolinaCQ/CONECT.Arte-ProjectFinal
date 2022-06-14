package edu.team5.finalproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {
    
    INVALID_GROUP_NAME("El nombre de grupo/artísta ingresado es incorrecto."),
    INVALID_USERNAME("El nombre de usuario ingresado es incorrecto."),
    INVALID_PASSWORD("La contraseña ingresada es incorrecta."),
    INVALID_EMAIL("El Correo electrónico ingresado es incorrecto."),
    INVALID_NUMBER("El número ingreaso es incorrecto."),
    ALREADY_EXISTS_EMAIL("El Correo electrónico ingresado ya se encuentra en uso."),
    ALREADY_EXISTS_USERNAME("El nombre de usuario ingresado ya se encuentra en uso."),
    ALREADY_EXISTS_GROUP_NAME("El nombre de grupo/artísta ingresado ya se encuentra en uso."),

    SUCCESS_STATUS_CODE_200("Su petición se ha realizado con éxito."),
    ERROR_STATUS_CODE_300("Lo sentimos, la página solicitada pudo haber sido modificada o se encuentra en una URI distinta."),
    ERROR_STATUS_CODE_403("Acceso denegado, no tiene los permisos para acceder a esta página, verifique si se encuentra en sesión o comuníquese con la administración."),
    ERROR_STATUS_CODE_404("Lo sentimos, La direccion ingresada no existe o podria no estar disponible en este momento."),
    ERROR_STATUS_CODE_500("Lo sentimos, ha ocurrido un error en nuestros servidores o la página solicitada se encuentra en mantenimiento."),
    ERROR_STATUS_CODE_DEFAULT("Error inesperado al cargar la pagina, por favor, actualize la página o intente ingresar mas tarde.");

    private String name;
}

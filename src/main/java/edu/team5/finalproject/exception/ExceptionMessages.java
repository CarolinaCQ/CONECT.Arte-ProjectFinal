package edu.team5.finalproject.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionMessages {
    
    INVALID_GROUP_NAME("El nombre de grupo/artísta ingresado es incorrecto."),
    INVALID_USERNAME("El nombre de usuario ingresado es incorrecto."),
    INVALID_PASSWORD("La contraseña ingresada es incorrecta."),
    INVALID_EMAIL("El correo electrónico ingresado es incorrecto."),
    INVALID_NUMBER("El número ingresado es incorrecto."),
    INVALID_AMOUNT_CHARACTER("La descripción ha superado el límite máximo de caracteres (2500)."),
    ALREADY_EXISTS_EMAIL("El correo electrónico ingresado ya se encuentra en uso."),
    ALREADY_EXISTS_USERNAME("El nombre de usuario ingresado ya se encuentra en uso."),
    ALREADY_EXISTS_GROUP_NAME("El nombre de grupo/artísta ingresado ya se encuentra en uso."),
    ALREADY_EXISTS_WHATSAPP_NUMBER("El número de contacto ingresado ya se encuentra en uso."),

    SUCCESS_STATUS_CODE_200("Su petición se ha realizado con éxito."),
    ERROR_STATUS_CODE_300("Lo sentimos, la página solicitada pudo haber sido modificada o se encuentra en una URI distinta."),
    ERROR_STATUS_CODE_403("Acceso denegado, no tiene los permisos para acceder a esta página, verifique si se encuentra en sesión o comuníquese con la administración."),
    ERROR_STATUS_CODE_404("Lo sentimos, La dirección ingresada no existe o podria no estar disponible en este momento."),
    ERROR_STATUS_CODE_500("Lo sentimos, ha ocurrido un error en nuestros servidores o la página solicitada se encuentra en mantenimiento."),
    ERROR_STATUS_CODE_DEFAULT("Error inesperado al cargar la página, por favor, actualize la página o intente ingresar mas tarde."),

    ERROR_SAVING_IMAGE("Error al guardar la imagen."),
    ERROR_FORMAT_IMAGE("Error al guardar la imagen, el formato de la imagen debe ser (.png) o (.jpg)."),
    ERROR_SIZE_IMAGE("Error al guardar la imagen, el tamaño de la imagen máximo permitido es de 1 MB."),

    INFO_GROUP_NAME("No se permiten caracteres especiales."),
    INFO_USERNAME("El nombre de usuario no debe contener espacios."),
    INFO_EMAIL("Ejemplo: contact.Arte@hotmail.com"),
    INFO_PASSWORD("Mínimo de 4, máximo 16, al menos una minúscula, al menos una mayúscula y al menos un número"),
    INFO_IMAGE("El formato de la imagen debe ser .jpg o .png y debe tener un máximo de 1 mb.");

    private String value;

    public String get(){
        return value;
    }
}

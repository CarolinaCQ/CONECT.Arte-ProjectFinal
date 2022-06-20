package edu.team5.finalproject.utility;

import java.util.regex.Pattern;

public class Utility {

    public static final String NAME_PATTERN = "^[a-zA-Z0-9À-ÿ\\s]+{4,16}$";  //se permite: minúsculas, mayúsculas, acentos, numeros y espacios / nombre grupo
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9À-ÿ\\s\\_\\-]+{4,16}$"; //se permite: minúsculas, mayúsculas, acentos, numeros, espacios y guiones / usuario cliente
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\S])[A-Za-z\\d\\S]{4,16}$"; // Mínimo de 4, máximo 16, al menos una minúscula, al menos una mayúscula y al menos un número.
    public static final String ONLY_NUMBERS_PATTERN = "^[0-9]+$"; //solo numeros

    public static boolean validate(String pattern, String value){
      return Pattern.compile(pattern).matcher(value).find();        
    }      
}



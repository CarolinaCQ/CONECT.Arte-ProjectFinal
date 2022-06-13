package edu.team5.finalproject.utility;

import java.util.Optional;
import java.util.regex.Pattern;

import edu.team5.finalproject.exception.MyException;

public class Utility {

    private static boolean flag;
    public static final String ONLY_NAMES = "[a-zA-Z]+";
    public static final String USERNAME = "[a-zA-Z0-9]";
    public static final String MAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\S])[A-Za-z\\d\\S]{6,12}$";
    public static final String ONLY_NUMBERS = "[0-9]+";

    public static void validate(String pattern, String value) throws MyException{
        flag = Pattern.compile(pattern).matcher(value).find();
        if(!flag) throw new MyException("Exception message here.");        //preguntar si usar true o false para la condición
    }  

   /* 
    *   public static <T> T controlOptional(Optional<T> optional) {        
        return optional.get();   
    }   
   */
     
     
    

}



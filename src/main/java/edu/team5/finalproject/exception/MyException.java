package edu.team5.finalproject.exception;
          
public class MyException extends Exception{

    public MyException(String exception){
        super(exception);
    }

    public MyException(ExceptionMessages exception){
        super(exception.toString());
    }

}
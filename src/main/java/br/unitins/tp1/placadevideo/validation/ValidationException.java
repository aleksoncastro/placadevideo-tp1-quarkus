package br.unitins.tp1.placadevideo.validation;

public class ValidationException extends RuntimeException{
    private String fildName;

    public ValidationException(String filedName, String message){
        super(message);
        this.fildName = filedName;
    }

    public String getFildName(){
        return fildName;
    }

}

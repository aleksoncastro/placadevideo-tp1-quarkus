package br.unitins.tp1.placadevideo.validation;

public class ValidationException extends RuntimeException{
    private String fildName;

    public ValidationException(String fildName, String message){
        super(message);
        this.fildName = fildName;
    }

    public String getFildName(){
        return fildName;
    }

}

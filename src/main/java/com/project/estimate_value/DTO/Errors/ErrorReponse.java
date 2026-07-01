package com.project.estimate_value.DTO.Errors;

public class ErrorReponse {
    private String mensagem;
    private int status;

    public ErrorReponse(String mensagem, int status){
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem(){
        return mensagem;
    }

    public int getStatus(){
        return status;
    }

}

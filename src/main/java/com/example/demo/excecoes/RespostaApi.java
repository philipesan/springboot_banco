package com.example.demo.excecoes;

import java.util.List;

public class RespostaApi {

    private String status;
    private String mensagem;
    private List<Object> objCriado;

    public Object getObjCriado() {
        return objCriado;
    }

    public void setObjCriado(List<Object> objCriado) {
        this.objCriado = objCriado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public RespostaApi() {
    }

    public RespostaApi(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public RespostaApi(String status, String mensagem, List<Object> objCriado) {
        this.status = status;
        this.mensagem = mensagem;
        this.objCriado = objCriado;
    }

    @Override
    public String toString() {
        return "ErroApi{" +
                "status='" + status + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}

package io.github.guilhermemelo01.msavaliadorcredito.application.ex;

public class ErroComunicacaoMicroserviceException extends Exception {

    private Integer status;

    public ErroComunicacaoMicroserviceException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}

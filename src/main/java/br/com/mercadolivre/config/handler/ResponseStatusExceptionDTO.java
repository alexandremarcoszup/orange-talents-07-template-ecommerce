package br.com.mercadolivre.config.handler;

public class ResponseStatusExceptionDTO {

    private String erro;

    public ResponseStatusExceptionDTO(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
}

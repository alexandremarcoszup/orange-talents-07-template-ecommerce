package br.com.mercadolivre.config.validacao;

public class ErroDeRequestDTO {
    private String campo;
    private String erro;

    public ErroDeRequestDTO() {
    }

    public ErroDeRequestDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}

package br.com.mercadolivre.controller.response;

public class CaracteristicaDetalheResponse {

    private String nome;

    private String descricao;

    public CaracteristicaDetalheResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

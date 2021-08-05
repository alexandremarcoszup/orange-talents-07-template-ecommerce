package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.modelo.Caracteristica;
import br.com.mercadolivre.domain.modelo.Produto;

public class CaracteristicaRequest {

    private String nome;

    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica requestToDomain(Produto produto){
        return new Caracteristica(this.nome, this.descricao, produto);
    }

    public String getNome() {
        return nome;
    }
}

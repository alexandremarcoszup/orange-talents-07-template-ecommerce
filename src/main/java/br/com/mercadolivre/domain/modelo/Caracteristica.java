package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.CaracteristicaResponse;

import javax.persistence.*;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public CaracteristicaResponse domainToResponse(){
        return new CaracteristicaResponse(this.id, this.nome, this.descricao);
    }
}

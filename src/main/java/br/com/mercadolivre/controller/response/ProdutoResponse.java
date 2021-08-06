package br.com.mercadolivre.controller.response;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoResponse {

    private Long id;

    private String nome;

    private BigDecimal valor;

    private int quantidade;

    private String descricao;

    private Set<CaracteristicaResponse> caracteristica;

    private CategoriaResponse categoria;

    private Set<ImagemResponse> imagens;

    public ProdutoResponse(Long id, String s, String nome, BigDecimal valor, int quantidade, String descricao,
                           Set<CaracteristicaResponse> caracteristicas, CategoriaResponse categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristica = caracteristicas;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaResponse> getCaracteristica() {
        return caracteristica;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public Set<ImagemResponse> getImagens() {
        return imagens;
    }

    public void setImagens(Set<ImagemResponse> imagens) {
        this.imagens = imagens;
    }
}

package br.com.mercadolivre.controller.response;

import br.com.mercadolivre.domain.modelo.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class DetalhaProdutoResponse {

    private String nome;

    private BigDecimal preco;

    private String descricao;

    private Set<CaracteristicaDetalheResponse> caracteristicas;

    private Set<String> linksImagens;

    private SortedSet<String> perguntas;

    private Set<Map<String, String>> opnioes;

    private Double mediaDasNotas;

    private Integer quantidadeTotalDeNotas;

    public DetalhaProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapCaracteristicas(Caracteristica::domainToDetalheResponse);
        this.linksImagens = produto.mapImagens(ImagensProduto::getLink);
        this.perguntas = produto.mapPerguntas(Pergunta::getTitulo);

        Opnioes opnioes = produto.getOpnioes();

        this.opnioes = opnioes.mapOpnioes(opniao -> {
            return Map.of("titulo", opniao.getTitulo(), "descricao", opniao.getDescricao());
        });

        this.mediaDasNotas = opnioes.notaMedia();

        this.quantidadeTotalDeNotas = opnioes.totalOpnioes();

    }
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<CaracteristicaDetalheResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpnioes() {
        return opnioes;
    }


    public Double getMediaDasNotas() {
        return mediaDasNotas;
    }
}

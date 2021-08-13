package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.config.validator.ExistsId;
import br.com.mercadolivre.domain.enums.GatewayDePagamento;
import br.com.mercadolivre.domain.enums.StatusCompra;
import br.com.mercadolivre.domain.modelo.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class CompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    private GatewayDePagamento gateway;

    public CompraRequest(@NotNull @Positive Integer quantidade, @NotNull Long idProduto, @NotBlank GatewayDePagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayDePagamento getGateway() {
        return gateway;
    }

    public Compra requestToDomain(Usuario usuario, Produto produto) {
        return new Compra(usuario, produto, this.quantidade, this.gateway, StatusCompra.INICIADA);
    }
}

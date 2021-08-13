package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.enums.StatusRetornoPagSeguro;
import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.modelo.Transacao;

import javax.validation.constraints.NotNull;

public class FinalizaCompraRequestPagSeguro implements RetornoGatewayPagamento {

    @NotNull
    private Long idTransacao;

    @NotNull
    private StatusRetornoPagSeguro statusRetornoPagSeguro;

    public FinalizaCompraRequestPagSeguro(@NotNull Long idTransacao, @NotNull StatusRetornoPagSeguro statusRetornoPagSeguro) {
        this.idTransacao = idTransacao;
        this.statusRetornoPagSeguro = statusRetornoPagSeguro;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(this.idTransacao, this.statusRetornoPagSeguro.normaliza(), compra);
    }
}

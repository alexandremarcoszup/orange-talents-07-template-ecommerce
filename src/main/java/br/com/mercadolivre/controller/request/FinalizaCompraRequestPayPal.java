package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.config.validator.UniqueValue;
import br.com.mercadolivre.domain.enums.StatusTransacao;
import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.modelo.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FinalizaCompraRequestPayPal implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    @NotNull
    private Integer statusRetornoPaypal;

    @NotNull
    @UniqueValue(domainClass = Transacao.class, fieldName = "id")
    private Long idTransacao;

    public FinalizaCompraRequestPayPal(@Min(0) @Max(1) @NotNull Integer statusRetornoPaypal, @NotNull Long idTransacao) {
        this.statusRetornoPaypal = statusRetornoPaypal;
        this.idTransacao = idTransacao;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        StatusTransacao status = this.statusRetornoPaypal == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(this.idTransacao, status, compra);
    }
}

package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.modelo.Transacao;

public interface RetornoGatewayPagamento {

    /**
     * @param compra
     * @return ua transacao em função do gateway de pagamento específico
     */
    Transacao toTransacao(Compra compra);
}

package br.com.mercadolivre.domain.enums;

import static br.com.mercadolivre.domain.enums.StatusTransacao.sucesso;

public enum StatusCompra {

    INICIADA,
    FINALIZADA,
    FALHA;

    public StatusCompra evolui(StatusTransacao statusTransacao){
        if (statusTransacao.equals(sucesso))
            return FINALIZADA;
        else
            return FALHA;
    }

}

package br.com.mercadolivre.controller.request;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NotaFiscalRequest(@NotNull Long idCompra, @NotNull Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

}

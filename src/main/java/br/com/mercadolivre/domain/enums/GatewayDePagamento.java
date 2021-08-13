package br.com.mercadolivre.domain.enums;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayDePagamento {

    PAYPAL{
        @Override
        public String getURI(Long idCompra) {
            return "pagseguro.com?returnId={"+idCompra+"}&redirectUrl="
                    +getUriComponentsBuilder()
                            .path("/retorno-paypal/{id}")
                            .buildAndExpand(idCompra.toString())
                    .toUri();
        }
    },
    PAGSEGURO{
        @Override
        public String getURI(Long idCompra) {
            return "pagseguro.com?returnId={"+idCompra+"}&redirectUrl="
                    +getUriComponentsBuilder()
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(idCompra.toString())
                    .toUri();
        }
    };

    private UriComponentsBuilder uriComponentsBuilder;

    abstract public String getURI(Long idCompra);

    public GatewayDePagamento buildUriComponentsBuilder(UriComponentsBuilder uriComponentsBuilder){
        this.uriComponentsBuilder = uriComponentsBuilder;
        return this;
    }

    public UriComponentsBuilder getUriComponentsBuilder(){
        return this.uriComponentsBuilder;
    }

}

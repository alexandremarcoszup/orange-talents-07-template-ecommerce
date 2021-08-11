package br.com.mercadolivre.domain.modelo;

import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario comprador;

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private GatewayDePagamento pagamento;

    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    @Deprecated
    public Compra(){}

    public Compra(Usuario comprador, Produto produto, Integer quantidade, GatewayDePagamento pagamento, StatusCompra statusCompra) {
        this.comprador = comprador;
        this.produto = produto;
        this.quantidade = quantidade;
        this.pagamento = pagamento;
        this.statusCompra = statusCompra;
    }

    public String getEmailComprador() {
        return comprador.getEmail();
    }

    public String getEmailDono(){
        return this.produto.getUsuario().getEmail();
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public Long getId() {
        return id;
    }
}

package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.request.RetornoGatewayPagamento;
import br.com.mercadolivre.domain.enums.GatewayDePagamento;
import br.com.mercadolivre.domain.enums.StatusCompra;
import br.com.mercadolivre.domain.enums.StatusTransacao;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getVendedor(){
        return this.produto.getUsuario();
    }

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

    public String getEmailDono() {
        return this.produto.getUsuario().getEmail();
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public Long getId() {
        return id;
    }


    public void fazerPagamento(RetornoGatewayPagamento finalizaCompraRequest) {
        Transacao novaTransacao = finalizaCompraRequest.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Existe outra transacao igual a essa" + novaTransacao.toString());

        Assert.isTrue(transacoesFeitasComSucesso().isEmpty(), "Esta compra já foi feita com sucesso.");
        this.transacoes.add(finalizaCompraRequest.toTransacao(this));
        evoluiStatusCompra(finalizaCompraRequest.toTransacao(this).getStatusTransacao());
    }

    public Set<Transacao> transacoesFeitasComSucesso(){
        Set<Transacao> transacoesSucedidas = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());
        Assert.isTrue(transacoesSucedidas.size() <= 1, "Tem mais de uma transação concluída com sucesso nessa compra: "+this.id);

        return transacoesSucedidas;
    }

    public boolean finalizadaComSucesso() {
        return !transacoesFeitasComSucesso().isEmpty();
    }

    public void evoluiStatusCompra(StatusTransacao statusTransacao){
        this.statusCompra = this.statusCompra.evolui(statusTransacao);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", comprador=" + comprador +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", pagamento=" + pagamento +
                ", statusCompra=" + statusCompra +
                ", transacoes=" + transacoes +
                '}';
    }
}

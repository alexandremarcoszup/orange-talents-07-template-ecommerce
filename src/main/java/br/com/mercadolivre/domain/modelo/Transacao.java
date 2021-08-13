package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.domain.enums.StatusTransacao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idTansacaoGateway;

    @Column(nullable = false)
    private StatusTransacao statusTransacao;

    @Column(nullable = false)
    private LocalDateTime horarioTransacao;

    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao(){}

    public Transacao(Long idTransacao, StatusTransacao statusTransacao, Compra compra) {
        this.statusTransacao = statusTransacao;
        this.idTansacaoGateway = idTransacao;
        this.horarioTransacao = LocalDateTime.now();
        this.compra = compra;
    }

    public boolean concluidaComSucesso(){
        return this.statusTransacao.equals(StatusTransacao.sucesso);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", idTansacaoGateway=" + idTansacaoGateway +
                ", statusTransacao=" + statusTransacao +
                ", horarioTransacao=" + horarioTransacao +
                '}';
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
}

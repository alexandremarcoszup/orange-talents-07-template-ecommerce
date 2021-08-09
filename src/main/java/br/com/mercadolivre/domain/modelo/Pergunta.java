package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.PerguntaResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    private LocalDateTime instante = LocalDateTime.now();

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuarioInteressado;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, String descricao, Produto produto, Usuario usuarioInteressado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuarioInteressado = usuarioInteressado;
    }

    public PerguntaResponse domainToResponse() {
        return new PerguntaResponse(this.id, this.titulo, this.descricao, this.instante, this.produto.domainToReponse(),
                this.usuarioInteressado.domainToResponse());
    }

    public Usuario getUsuarioInteressado() {
        return usuarioInteressado;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmailPessoaInteressada(){
        return this.usuarioInteressado.getEmail();
    }

    public String getEmailDonoProduto(){
        return this.getProduto().getUsuario().getEmail();
    }
}

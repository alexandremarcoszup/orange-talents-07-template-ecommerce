package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.PerguntaResponse;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private LocalDateTime instante = LocalDateTime.now();

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuarioInteressado;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Produto produto, Usuario usuarioInteressado) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuarioInteressado = usuarioInteressado;
    }

    public PerguntaResponse domainToResponse() {
        return new PerguntaResponse(this.id, this.titulo, this.instante, this.produto.domainToReponse(),
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(titulo, pergunta.titulo) && Objects.equals(produto, pergunta.produto) && Objects.equals(usuarioInteressado, pergunta.usuarioInteressado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, produto, usuarioInteressado);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}

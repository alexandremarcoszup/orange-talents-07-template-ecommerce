package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.OpniaoResponse;

import javax.persistence.*;

@Entity
public class Opniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Short nota;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @Deprecated
    public Opniao(){}

    public Opniao(Short nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public OpniaoResponse domainToResponse(){
        return new OpniaoResponse(this.id, this.titulo, this.descricao, this.usuario.domainToResponse(), this.produto.domainToReponse());
    }
}

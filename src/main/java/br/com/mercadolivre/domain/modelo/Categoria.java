package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.CategoriaResponse;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public CategoriaResponse domainToResponse() {

        CategoriaResponse categoriaResponse = new CategoriaResponse(this.id, this.nome);
        if (this.categoriaMae != null) {
            categoriaResponse.setCategoriaMae(this.categoriaMae.domainToResponse());
        }

        return categoriaResponse;
    }
}

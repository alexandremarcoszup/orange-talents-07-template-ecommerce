package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.ImagemResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ImagensProduto {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagensProduto(){}

    public ImagensProduto(@NotNull String link, Produto produto){
        this.link = link;
        this.produto = produto;
    }

    public ImagemResponse domainToResponse(){
        return new ImagemResponse(this.id, this.link);
    }

    public String getLink() {
        return link;
    }
}

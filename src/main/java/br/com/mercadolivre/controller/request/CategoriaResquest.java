package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.config.validator.UniqueValue;
import br.com.mercadolivre.domain.modelo.Categoria;
import br.com.mercadolivre.domain.repository.CategoriaRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaResquest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    private Long idCategoriaMae;

    public CategoriaResquest(@NotBlank String nome, Long idCategoriaMae){
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria requestToDomain(CategoriaRepository categoriaRepository) {
        if (this.idCategoriaMae != null) {
            Optional<Categoria> categoriaMaeOp = categoriaRepository.findById(idCategoriaMae);
            return  categoriaMaeOp.map(categoriaMae -> new Categoria(this.nome, categoriaMae)).orElseGet(() -> new Categoria(this.nome));
        }else{
            return new Categoria(this.nome);
        }
    }
}

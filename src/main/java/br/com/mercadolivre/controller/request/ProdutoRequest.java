package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.config.validator.ExistsId;
import br.com.mercadolivre.domain.modelo.Categoria;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public ProdutoRequest(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull Integer quantidade, @NotBlank String descricao,
                          @Size(min = 3) List<CaracteristicaRequest> caracteristicas, @NotBlank Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto requestToDomain(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);

        return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria.get(),
                this.caracteristicas);
    }

    public HashSet<String> buscaCaracteristicasIguais() {
        HashSet<String> nomes = new HashSet<>();
        HashSet<String> nomeRepetidos = new HashSet<>();

        for (CaracteristicaRequest caracteristica : caracteristicas) {
            if (!(nomes.add(caracteristica.getNome()))) {
                nomeRepetidos.add(caracteristica.getNome());
            }
        }

        return nomeRepetidos;
    }


    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }
}

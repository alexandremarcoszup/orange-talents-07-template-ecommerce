package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.request.CaracteristicaRequest;
import br.com.mercadolivre.controller.response.ProdutoResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private int quantidade;

    @Column(length = 1000, nullable = false)
    private String descricao;

    private LocalDateTime horaCadastro;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull BigDecimal valor,@NotNull int quantidade, @NotBlank @Length(max = 1000) String descricao,
                   Categoria categoria,@Size(min = 3) Collection<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.horaCadastro = LocalDateTime.now();
        this.categoria = categoria;
        this.caracteristicas = caracteristicas.stream().map(request -> request.requestToDomain(this)).collect(Collectors.toSet());

        Assert.isTrue(caracteristicas.size() >= 3, "Deveria ter mais que 3 caracteristicas!!!");
    }

    public ProdutoResponse domainToReponse() {
        return new ProdutoResponse(this.id, this.nome, this.nome, this.valor, this.quantidade, this.descricao, this.caracteristicas.stream().map(Caracteristica::domainToResponse).collect(Collectors.toSet()), this.categoria.domainToResponse());
    }
}

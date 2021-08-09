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
import java.util.*;
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
    private Integer quantidade;

    @Column(length = 1000, nullable = false)
    private String descricao;

    private LocalDateTime horaCadastro;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagensProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull BigDecimal valor,@NotNull Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
                   Categoria categoria,@Size(min = 3) Collection<CaracteristicaRequest> caracteristicas, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.horaCadastro = LocalDateTime.now();
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas = caracteristicas.stream().map(request -> request.requestToDomain(this)).collect(Collectors.toSet());

        Assert.isTrue(caracteristicas.size() >= 3, "Deveria ter mais que 3 caracteristicas!!!");
    }

    public ProdutoResponse domainToReponse() {
        ProdutoResponse response = new ProdutoResponse(this.id, this.nome, this.nome, this.valor, this.quantidade, this.descricao,
                this.caracteristicas.stream().map(Caracteristica::domainToResponse).collect(Collectors.toSet()),
                this.categoria.domainToResponse());
        if (!this.imagens.isEmpty())
            response.setImagens(this.imagens.stream().map(ImagensProduto::domainToResponse).collect(Collectors.toSet()));
        return response;
    }

    public void associaImagemAProduto(List<String> imageLinks){
        this.imagens = imageLinks.stream().map(link -> new ImagensProduto(link, this)).collect(Collectors.toSet());
    }

    public boolean pertenceAoDono(Optional<Usuario> dono){
        return this.usuario.equals(dono.get());
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

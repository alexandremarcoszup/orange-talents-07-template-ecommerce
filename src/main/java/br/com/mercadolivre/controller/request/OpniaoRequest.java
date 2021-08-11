package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.modelo.Opniao;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpniaoRequest {

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Short nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public OpniaoRequest(Short nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opniao requestToDomain(Usuario usuario, Produto produto) {
        return new Opniao(this.nota, this.titulo, this.descricao, produto, usuario);
    }
}

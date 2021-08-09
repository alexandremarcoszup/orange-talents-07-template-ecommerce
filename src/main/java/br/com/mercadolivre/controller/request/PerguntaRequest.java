package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.modelo.Pergunta;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @NotBlank @Length(min = 1, max = 500)
    private String descricao;

    public PerguntaRequest(@NotBlank String titulo, @NotBlank @Length(min = 1, max = 500) String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pergunta requestToDomain(Usuario usuarioInteressado, Produto produto){
        return new Pergunta(this.titulo, this.descricao, produto, usuarioInteressado);
    }
}

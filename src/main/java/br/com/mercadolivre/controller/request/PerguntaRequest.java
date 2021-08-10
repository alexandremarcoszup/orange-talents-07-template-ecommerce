package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.modelo.Pergunta;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public PerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta requestToDomain(Usuario usuarioInteressado, Produto produto){
        return new Pergunta(this.titulo, produto, usuarioInteressado);
    }
}

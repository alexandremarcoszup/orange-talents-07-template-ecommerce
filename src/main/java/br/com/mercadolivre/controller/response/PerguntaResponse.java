package br.com.mercadolivre.controller.response;

import java.time.LocalDateTime;

public class PerguntaResponse {

    private Long id;

    private String titulo;

    private LocalDateTime horaRegistro;

    private ProdutoResponse produto;

    private UsuarioResponse usuarioInteressado;

    public PerguntaResponse(Long id, String titulo, LocalDateTime horaRegistro, ProdutoResponse produto, UsuarioResponse usuarioInteressado) {
        this.id = id;
        this.titulo = titulo;
        this.horaRegistro = horaRegistro;
        this.produto = produto;
        this.usuarioInteressado = usuarioInteressado;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getHoraRegistro() {
        return horaRegistro;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }

    public UsuarioResponse getUsuarioInteressado() {
        return usuarioInteressado;
    }
}

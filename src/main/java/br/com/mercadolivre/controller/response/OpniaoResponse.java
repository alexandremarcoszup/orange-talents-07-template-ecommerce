package br.com.mercadolivre.controller.response;

public class OpniaoResponse {

    private Long id;

    private String titulo;

    private String descricao;

    private UsuarioResponse usuarioResponse;

    private ProdutoResponse produtoResponse;

    public OpniaoResponse(Long id, String titulo, String descricao, UsuarioResponse usuarioResponse, ProdutoResponse produtoResponse) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioResponse = usuarioResponse;
        this.produtoResponse = produtoResponse;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public UsuarioResponse getUsuarioResponse() {
        return usuarioResponse;
    }

    public ProdutoResponse getProdutoResponse() {
        return produtoResponse;
    }
}

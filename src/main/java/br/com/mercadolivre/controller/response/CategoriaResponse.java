package br.com.mercadolivre.controller.response;

public class CategoriaResponse {

    private Long id;

    private String nome;

    private CategoriaResponse categoriaMae;

    public CategoriaResponse(Long id, String nome, CategoriaResponse categoriaMae) {
        this.id = id;
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public CategoriaResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaResponse getCategoriaMae() {
        return categoriaMae;
    }

    public void setCategoriaMae(CategoriaResponse categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}

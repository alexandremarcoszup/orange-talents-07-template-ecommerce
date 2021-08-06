package br.com.mercadolivre.controller.response;

public class ImagemResponse {

    private Long id;

    private String imageLink;

    public ImagemResponse(Long id, String imageLink){
        this.id = id;
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public String getImageLink() {
        return imageLink;
    }
}

package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.config.validator.DiferentNames;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemProdutoRequest {

    @Size(min = 1)
    @NotNull
    @DiferentNames
    private List<MultipartFile> imagens = new ArrayList<>();

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }


}
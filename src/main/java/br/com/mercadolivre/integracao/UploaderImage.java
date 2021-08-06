package br.com.mercadolivre.integracao;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploaderImage {

    List<String> generateImageLinks(List<MultipartFile> imagens);
}

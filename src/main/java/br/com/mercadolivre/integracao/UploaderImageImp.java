package br.com.mercadolivre.integracao;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UploaderImageImp implements UploaderImage {

    @Override
    public List<String> generateImageLinks(List<MultipartFile> imagens){
        return imagens.stream().map(imagem -> "http://imagem.io/"+imagem.getOriginalFilename()).collect(Collectors.toList());
    }
}

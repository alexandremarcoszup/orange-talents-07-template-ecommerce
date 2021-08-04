package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.CategoriaResquest;
import br.com.mercadolivre.controller.response.CategoriaResponse;
import br.com.mercadolivre.domain.modelo.Categoria;
import br.com.mercadolivre.domain.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastraCategoria(@RequestBody @Valid CategoriaResquest categoriaResquest){

        Categoria categoria = categoriaRepository.save(categoriaResquest.requestToDomain(categoriaRepository));

        return ResponseEntity.ok(categoria.domainToResponse());
    }
}

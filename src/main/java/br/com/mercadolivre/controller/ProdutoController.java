package br.com.mercadolivre.controller;

import br.com.mercadolivre.config.validator.ValidaCaracteristicasiguais;
import br.com.mercadolivre.controller.request.ProdutoRequest;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.repository.CategoriaRepository;
import br.com.mercadolivre.domain.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ValidaCaracteristicasiguais());
    }

    @PostMapping
    public ResponseEntity<?> cadastraProduto(@RequestBody @Valid ProdutoRequest produtoRequest){

        Produto produto = produtoRepository.save(produtoRequest.requestToDomain(categoriaRepository));


        return ResponseEntity.ok(produto.domainToReponse());
    }
}

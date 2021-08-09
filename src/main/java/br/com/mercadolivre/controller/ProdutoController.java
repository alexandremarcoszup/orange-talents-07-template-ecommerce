package br.com.mercadolivre.controller;

import br.com.mercadolivre.config.validator.ValidaCaracteristicasiguais;
import br.com.mercadolivre.controller.request.ImagemProdutoRequest;
import br.com.mercadolivre.controller.request.ProdutoRequest;
import br.com.mercadolivre.controller.response.ProdutoResponse;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import br.com.mercadolivre.domain.repository.CategoriaRepository;
import br.com.mercadolivre.domain.repository.ProdutoRepository;
import br.com.mercadolivre.domain.repository.UsuarioRepository;
import br.com.mercadolivre.integracao.UploaderImage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;
    private UploaderImage upLoaderImageImp;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
                             UsuarioRepository usuarioRepository, UploaderImage upLoaderImageImp) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.upLoaderImageImp = upLoaderImageImp;
    }

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ValidaCaracteristicasiguais());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastraProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail("alexandre@email.com");

        Produto produto = produtoRepository.save(produtoRequest
                .requestToDomain(categoriaRepository, usuario.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuário não existente"))));


        return ResponseEntity.ok(produto.domainToReponse());
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> cadastraImagensEmProdutos(
            @PathVariable("id") Long idProduto, @Valid
            ImagemProdutoRequest imagemProdutoRequest) {

        Optional<Usuario> dono = usuarioRepository.findByEmail("alexandre@email.com");
        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if (produto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existente");
        }
        if (produto.get().pertenceAoDono(dono)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.get().associaImagemAProduto(upLoaderImageImp.generateImageLinks(imagemProdutoRequest.getImagens()));

        produtoRepository.save(produto.get());

        return ResponseEntity.ok(produto.get().domainToReponse());
    }
}

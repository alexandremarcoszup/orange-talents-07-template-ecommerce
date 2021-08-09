package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.OpniaoRequest;
import br.com.mercadolivre.controller.response.OpniaoResponse;
import br.com.mercadolivre.domain.modelo.Opniao;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import br.com.mercadolivre.domain.repository.OpniaoRepository;
import br.com.mercadolivre.domain.repository.ProdutoRepository;
import br.com.mercadolivre.domain.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class OpniaoController {

    private ProdutoRepository produtoRepository;
    private OpniaoRepository opniaoRepository;

    public OpniaoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, OpniaoRepository opniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.opniaoRepository = opniaoRepository;
    }

    @PostMapping("/{id}/opniao")
    public ResponseEntity<OpniaoResponse> postaOpniao(@AuthenticationPrincipal Object usuarioLogado, @PathVariable("id") Long idProduto,
                                                      @RequestBody @Valid OpniaoRequest opniaoRequest) {

        Usuario usuario = (Usuario) ((Optional) usuarioLogado).get();

        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existente");
        }

        Opniao opniao = opniaoRepository.save(opniaoRequest.requestToDomain(usuario, produto.get()));

        return ResponseEntity.ok(opniao.domainToResponse());
    }
}

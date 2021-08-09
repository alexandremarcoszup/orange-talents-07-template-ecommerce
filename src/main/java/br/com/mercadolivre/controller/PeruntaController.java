package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.PerguntaRequest;
import br.com.mercadolivre.domain.modelo.Pergunta;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import br.com.mercadolivre.domain.repository.PerguntaRepository;
import br.com.mercadolivre.domain.repository.ProdutoRepository;
import br.com.mercadolivre.integracao.EmailSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class PeruntaController {

    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private EmailSender emailSender;

    public PeruntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, EmailSender emailSender) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.emailSender = emailSender;
    }

    @PostMapping("/{id}/pergunta")
    public ResponseEntity<?> postaPergunta(@AuthenticationPrincipal Object usuarioLogado, @PathVariable Long idProduto,
                                           @RequestBody PerguntaRequest perguntaRequest) {

        Usuario usuario = (Usuario) ((Optional) usuarioLogado).get();

        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existente");
        }

        Pergunta pergunta = perguntaRepository.save(perguntaRequest.requestToDomain(usuario, produto.get()));
        emailSender.sendEmailPergunta(pergunta);

        return ResponseEntity.ok(pergunta.domainToResponse());
    }
}

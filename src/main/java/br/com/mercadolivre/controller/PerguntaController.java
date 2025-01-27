package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.PerguntaRequest;
import br.com.mercadolivre.controller.response.PerguntaResponse;
import br.com.mercadolivre.domain.modelo.Pergunta;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import br.com.mercadolivre.domain.repository.PerguntaRepository;
import br.com.mercadolivre.domain.repository.ProdutoRepository;
import br.com.mercadolivre.service.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class PerguntaController {

    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private EmailSenderService emailSenderService;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, EmailSenderService emailSenderService) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/{id}/pergunta")
    public ResponseEntity<PerguntaResponse> postaPergunta(@AuthenticationPrincipal Object usuarioLogado, @PathVariable("id") Long idProduto,
                                                          @RequestBody @Valid PerguntaRequest perguntaRequest) {

        Usuario usuario = (Usuario) ((Optional) usuarioLogado).get();

        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existente");
        }

        Pergunta pergunta = perguntaRepository.save(perguntaRequest.requestToDomain(usuario, produto.get()));
        emailSenderService.sendEmailPergunta(pergunta);

        return ResponseEntity.ok(pergunta.domainToResponse());
    }
}

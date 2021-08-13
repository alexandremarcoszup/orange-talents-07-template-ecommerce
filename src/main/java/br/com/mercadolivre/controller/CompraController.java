package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.CompraRequest;
import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.modelo.Produto;
import br.com.mercadolivre.domain.modelo.Usuario;
import br.com.mercadolivre.domain.repository.CompraRepository;
import br.com.mercadolivre.domain.repository.ProdutoRepository;
import br.com.mercadolivre.service.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.BindException;
import java.util.Optional;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private ProdutoRepository produtoRepository;
    private EmailSenderService emailService;
    private CompraRepository compraRepository;

    public CompraController(ProdutoRepository produtoRepository, EmailSenderService emailService,
                            CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.emailService = emailService;
        this.compraRepository = compraRepository;
    }

    @PostMapping
    public ResponseEntity<String> compraProduto(@AuthenticationPrincipal Object usuarioLogado,
                                           @RequestBody @Valid CompraRequest compraRequest,
                                           UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Usuario usuario = (Usuario) ((Optional) usuarioLogado).get();

        Optional<Produto> produto = produtoRepository.findById(compraRequest.getIdProduto());

        if (produto.get().abateCompras(compraRequest.getQuantidade())){
            Compra compra = compraRepository.save(compraRequest.requestToDomain(usuario, produto.get()));
            emailService.sendEmailCompra(compra);
            return ResponseEntity.ok(
                    compraRequest.getGateway()
                            .buildUriComponentsBuilder(uriComponentsBuilder)
                            .getURI(compra.getId()));
        }

        throw new BindException("Não existe quantidade suficiente para fazer a compra.");
    }

}

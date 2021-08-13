package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.FinalizaCompraRequestPagSeguro;
import br.com.mercadolivre.controller.request.FinalizaCompraRequestPayPal;
import br.com.mercadolivre.controller.request.RetornoGatewayPagamento;
import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.repository.CompraRepository;
import br.com.mercadolivre.service.EmailSenderService;
import br.com.mercadolivre.service.EventosNovaCompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FinalizaCompraController {

    private final CompraRepository compraRepository;
    private final EmailSenderService emailSenderService;
    private final EventosNovaCompraService eventosNovaCompraService;

    public FinalizaCompraController(CompraRepository compraRepository, EmailSenderService emailSenderService, EventosNovaCompraService eventosNovaCompraService) {
        this.eventosNovaCompraService = eventosNovaCompraService;
        this.compraRepository = compraRepository;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/pag-seguro/{id}")
    @Transactional
    public ResponseEntity<?> finalizaCompraPagSeguro(@PathVariable("id") Long idCompra, @Valid FinalizaCompraRequestPagSeguro finalizaCompraRequest){


        return ResponseEntity.ok(processaCompra(idCompra, finalizaCompraRequest));
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> finalizaCompraPaypal(@PathVariable("id") Long idCompra, @Valid FinalizaCompraRequestPayPal finalizaCompraRequest){


        return ResponseEntity.ok(processaCompra(idCompra, finalizaCompraRequest));
    }

    private String processaCompra(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamentoRequest){

        Optional<Compra> compraOptional = compraRepository.findById(idCompra);

        if (compraOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Compra não existente");
        }

        compraOptional.get().fazerPagamento(retornoGatewayPagamentoRequest);
        Compra compra = compraRepository.save(compraOptional.get());
        eventosNovaCompraService.processa(compra);

        return compra.toString();
    }
}

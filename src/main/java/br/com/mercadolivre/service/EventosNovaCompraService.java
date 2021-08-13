package br.com.mercadolivre.service;

import br.com.mercadolivre.domain.modelo.Compra;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompraService {

    private final EmailSenderService emailSenderService;
    private final Set<EventoCompraSucesso> eventoCompraSucesso;

    public EventosNovaCompraService(EmailSenderService emailSenderService, Set<EventoCompraSucesso> eventoCompraSucesso) {
        this.emailSenderService = emailSenderService;
        this.eventoCompraSucesso = eventoCompraSucesso;
    }

    public void processa(Compra compra) {

        if (compra.finalizadaComSucesso()){
            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
        }
        emailSenderService.sendEmailFinalizacaoCompra(compra);
    }
}

package br.com.mercadolivre.service.impl;

import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.modelo.Pergunta;
import br.com.mercadolivre.integracao.MailSender;
import br.com.mercadolivre.service.EmailSenderService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private MailSender mailSender;

    public EmailSenderServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailPergunta(@NotNull @Valid Pergunta pergunta) {
        mailSender.send("<html>...algo brabo...</html>", pergunta.getTitulo(),pergunta.getEmailPessoaInteressada(),
                pergunta.getEmailDonoProduto(), pergunta.getEmailPessoaInteressada());
    }

    @Override
    public void sendEmailCompra(Compra compra) {
        mailSender.send("<html>...algo brabo...</html>", "Status da compra: "+compra.getStatusCompra().toString(),
                compra.getEmailComprador(),
                compra.getEmailDono(),
                compra.getEmailComprador());
    }

    @Override
    public void sendEmailFinalizacaoCompra(Compra compra) {
        mailSender.send("<html>...algo brabo...</html>", "Status do pagamento: "+compra.getStatusCompra().toString(),
                compra.getEmailComprador(),
                compra.getEmailDono(),
                compra.getEmailComprador());
    }


}

package br.com.mercadolivre.integracao;

import br.com.mercadolivre.domain.modelo.Pergunta;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class EmailSenderImpl implements EmailSender {

    private MailSender mailSender;

    public EmailSenderImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailPergunta(@NotNull @Valid Pergunta pergunta) {
        mailSender.send("<html>...algo brabo...</html>", pergunta.getTitulo(),pergunta.getEmailPessoaInteressada(),
                pergunta.getEmailDonoProduto(), pergunta.getEmailPessoaInteressada());
    }



}

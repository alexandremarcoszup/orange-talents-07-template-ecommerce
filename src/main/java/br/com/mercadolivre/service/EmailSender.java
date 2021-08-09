package br.com.mercadolivre.integracao;

import br.com.mercadolivre.domain.modelo.Pergunta;

public interface EmailSender {

    void sendEmailPergunta(Pergunta pergunta);
}

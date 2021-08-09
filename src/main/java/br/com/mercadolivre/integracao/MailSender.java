package br.com.mercadolivre.integracao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface MailSender {

    /**
     * @param body corpo do email
     * @param subject titulo email
     * @param nameFrom nome para aparecer no provedor
     * @param from email de origem
     * @param to email de destino
     * */
    void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom, @NotBlank @Email String from,
              @NotBlank @Email String to);
}

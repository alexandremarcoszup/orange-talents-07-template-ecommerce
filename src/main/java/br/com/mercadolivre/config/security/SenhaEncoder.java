package br.com.mercadolivre.config.security;

public interface SenhaEncoder {

    String encode(String senha);
}

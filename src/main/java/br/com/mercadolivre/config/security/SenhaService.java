package br.com.mercadolivre.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SenhaService implements SenhaEncoder{

    public String encode(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}

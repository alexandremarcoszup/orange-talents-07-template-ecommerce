package br.com.mercadolivre.controller.request;

import br.com.mercadolivre.domain.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    private final String email;
    @NotBlank @Length(min = 6)
    private final String senha;

    public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6)String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario requestToDomain(){
        return new Usuario(this.email, this.senha);
    }
}

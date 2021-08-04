package br.com.mercadolivre.domain.modelo;

import br.com.mercadolivre.controller.response.UsuarioResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime horaRegistro;

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
        this.horaRegistro = LocalDateTime.now();
    }

    public UsuarioResponse domainToResponse(){
        return new UsuarioResponse(this.id,this.email, this.senha, this.horaRegistro);
    }

}

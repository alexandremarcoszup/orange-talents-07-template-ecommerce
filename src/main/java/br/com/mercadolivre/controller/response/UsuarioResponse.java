package br.com.mercadolivre.controller.response;

import java.time.LocalDateTime;

public class UsuarioResponse {
    private Long id;

    private String email;

    private String senha;

    private LocalDateTime horaRegistro;

    public UsuarioResponse(Long id, String email, String senha, LocalDateTime horaRegistro) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.horaRegistro = horaRegistro;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getHoraRegistro() {
        return horaRegistro;
    }
}

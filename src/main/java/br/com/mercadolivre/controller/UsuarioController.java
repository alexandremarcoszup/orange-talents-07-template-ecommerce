package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.UsuarioRequest;
import br.com.mercadolivre.controller.response.UsuarioResponse;
import br.com.mercadolivre.domain.modelo.Usuario;
import br.com.mercadolivre.domain.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastroUsuario(@RequestBody @Valid UsuarioRequest request){

        Usuario usuario = usuarioRepository.save(request.requestToDomain());

        return ResponseEntity.ok(usuario.domainToResponse());
    }
}

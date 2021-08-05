package br.com.mercadolivre.controller;

import br.com.mercadolivre.config.security.SenhaEncoder;
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
    private final SenhaEncoder senhaEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, SenhaEncoder senhaEncoder){
        this.usuarioRepository = usuarioRepository;
        this.senhaEncoder = senhaEncoder;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastroUsuario(@RequestBody @Valid UsuarioRequest request){

        Usuario usuario = usuarioRepository.save(request.requestToDomain(senhaEncoder));

        return ResponseEntity.ok(usuario.domainToResponse());
    }
}

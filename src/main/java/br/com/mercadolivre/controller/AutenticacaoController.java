package br.com.mercadolivre.controller;

import br.com.mercadolivre.config.security.TokenService;
import br.com.mercadolivre.controller.request.LoginRequest;
import br.com.mercadolivre.controller.response.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken dadoslogin = loginRequest.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadoslogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer "));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }


    }
}

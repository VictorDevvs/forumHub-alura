package br.com.forumhub.aluraOne.controller;

import br.com.forumhub.aluraOne.dto.UsuarioRequestLogin;
import br.com.forumhub.aluraOne.dto.UsuarioRequestRegistro;
import br.com.forumhub.aluraOne.dto.UsuarioResponseLogin;
import br.com.forumhub.aluraOne.dto.UsuarioResponseRegistro;
import br.com.forumhub.aluraOne.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseRegistro> save(@RequestBody @Valid UsuarioRequestRegistro request){
        UsuarioResponseRegistro response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseLogin> login(@RequestBody @Valid UsuarioRequestLogin request) {
        UsuarioResponseLogin response = service.login(request);
        return ResponseEntity.ok(response);
    }
}

package br.com.forumhub.aluraOne.handler;


import br.com.forumhub.aluraOne.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(TopicoExistenteException.class)
    public ResponseEntity<Details> handlerTopicoExistenteException(TopicoExistenteException ex) {
        Details details = new Details();
        details.setTitle("Tópico já existe");
        details.setMessage(ex.getMessage());
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setPath("/topicos");

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(TopicoNaoEncontradoException.class)
    public ResponseEntity<Details> handlerTopicoNaoEncontradoException(TopicoNaoEncontradoException ex) {
        Details details = new Details();
        details.setTitle("Tópico não encontrado");
        details.setMessage(ex.getMessage());
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setPath("/topicos/{id}");

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Details> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        Details details = new Details();
        details.setTitle("Usuário não encontrado");
        details.setMessage(ex.getMessage());
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setPath("/usuarios/login");

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<Details> handlerEmailExistenteException(EmailExistenteException ex) {
        Details details = new Details();
        details.setTitle("Email já cadastrado");
        details.setMessage(ex.getMessage());
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setPath("/usuarios/login");

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<Details> handlerSenhaIncorretaException(SenhaIncorretaException ex) {
        Details details = new Details();
        details.setTitle("Senha incorreta");
        details.setMessage(ex.getMessage());
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setPath("/usuarios/login");

        return ResponseEntity.status(details.getStatus()).body(details);
    }
}


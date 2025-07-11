package br.com.forumhub.aluraOne.dto;

import br.com.forumhub.aluraOne.model.Status;

import java.time.LocalDateTime;

public record TopicoResponse(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        String autor,
        String curso
) {
}

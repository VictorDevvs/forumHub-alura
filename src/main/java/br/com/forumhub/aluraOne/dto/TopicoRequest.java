package br.com.forumhub.aluraOne.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoRequest(
        @NotBlank(message = "O título não pode estar em branco")
        String titulo,

        @NotBlank(message = "A mensagem não pode estar em branco")
        String mensagem,

        @NotBlank(message = "O autor não pode estar em branco")
        String autor,

        @NotBlank(message = "O curso não pode estar em branco")
        String curso
) {
}

package br.com.forumhub.aluraOne.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestLogin(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {
}

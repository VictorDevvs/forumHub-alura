package br.com.forumhub.aluraOne.exceptions;

public class TopicoNaoEncontradoException extends RuntimeException {
    public TopicoNaoEncontradoException(String message) {
        super(message);
    }
}

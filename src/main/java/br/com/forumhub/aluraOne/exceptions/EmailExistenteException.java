package br.com.forumhub.aluraOne.exceptions;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String message) {
        super(message);
    }
}

package br.com.forumhub.aluraOne.exceptions;

public class SenhaIncorretaException extends RuntimeException {

    public SenhaIncorretaException(String message) {
        super(message);
    }
}

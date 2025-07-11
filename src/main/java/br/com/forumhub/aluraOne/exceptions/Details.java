package br.com.forumhub.aluraOne.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Details {

    private String title;
    private String message;
    private LocalDateTime timestamp;
    private int status;
    private String path;
}

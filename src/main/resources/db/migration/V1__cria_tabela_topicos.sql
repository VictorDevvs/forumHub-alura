CREATE TABLE topicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensagem VARCHAR(255) NOT NULL UNIQUE,
    data_criacao TIMESTAMP,
    status ENUM('RESPONDIDO', 'NAO_RESPONDIDO') NOT NULL,
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL
);
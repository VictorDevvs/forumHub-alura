package br.com.forumhub.aluraOne.repository;

import br.com.forumhub.aluraOne.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    Optional<Topico> findByTituloOrMensagem(String titulo, String mensagem);

}

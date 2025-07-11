package br.com.forumhub.aluraOne.service;

import br.com.forumhub.aluraOne.dto.TopicoRequest;
import br.com.forumhub.aluraOne.dto.TopicoResponse;
import br.com.forumhub.aluraOne.exceptions.TopicoExistenteException;
import br.com.forumhub.aluraOne.exceptions.TopicoNaoEncontradoException;
import br.com.forumhub.aluraOne.model.Status;
import br.com.forumhub.aluraOne.model.Topico;
import br.com.forumhub.aluraOne.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TopicoResponse save (TopicoRequest request){
        if (repository.findByTituloAndMensagem(request.titulo(), request.mensagem()).isPresent()) {
            throw new TopicoExistenteException("Tópico com título e mensagem iguais já existe");
        }

        if (repository.findByTituloOrMensagem(request.titulo(), request.mensagem()).isPresent()) {
            throw new TopicoExistenteException("Tópico com título ou mensagem iguais já existe");
        }


        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(Status.NAO_RESPONDIDO);
        topico.setAutor(request.autor());
        topico.setCurso(request.curso());

        Topico savedTopico = repository.save(topico);
        return new TopicoResponse(
                savedTopico.getTitulo(),
                savedTopico.getMensagem(),
                savedTopico.getDataCriacao(),
                savedTopico.getStatus(),
                savedTopico.getAutor(),
                savedTopico.getCurso()
        );
    }

    public Page<TopicoResponse> findAll(Pageable pageable){
        return repository.findAll(pageable).map(topico -> new TopicoResponse(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        ));
    }

    public TopicoResponse findById(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new TopicoNaoEncontradoException("Tópico com ID = " + id + " não encontrado"));

        return new TopicoResponse(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }

    @Transactional
    public TopicoResponse update(Long id, TopicoRequest request) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new TopicoNaoEncontradoException("Tópico com ID = " + id + " não encontrado para atualização"));

        repository.findByTituloAndMensagem(request.titulo(), request.mensagem())
                .filter(t -> !t.getId().equals(id))
                .ifPresent(t -> { throw new TopicoExistenteException("Tópico com título e mensagem iguais já existe"); });

        repository.findByTituloOrMensagem(request.titulo(), request.mensagem())
                .filter(t -> !t.getId().equals(id))
                .ifPresent(t -> { throw new TopicoExistenteException("Tópico com título ou mensagem iguais já existe"); });

        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setAutor(request.autor());
        topico.setCurso(request.curso());

        Topico updatedTopico = repository.save(topico);
        return new TopicoResponse(
                updatedTopico.getTitulo(),
                updatedTopico.getMensagem(),
                updatedTopico.getDataCriacao(),
                updatedTopico.getStatus(),
                updatedTopico.getAutor(),
                updatedTopico.getCurso()
        );
    }

    @Transactional
    public void delete(Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isPresent()){
            repository.deleteById(id);
        } else {
            throw new TopicoNaoEncontradoException("Tópico com ID = " + id + " não encontrado para exclusão");
        }
    }
}


package br.com.forumhub.aluraOne.controller;

import br.com.forumhub.aluraOne.dto.TopicoRequest;
import br.com.forumhub.aluraOne.dto.TopicoResponse;
import br.com.forumhub.aluraOne.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicoResponse> save (@RequestBody @Valid TopicoRequest request){
        TopicoResponse response = topicoService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<TopicoResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Pageable pageable) {
        Page<TopicoResponse> response = topicoService.findAll(Pageable.ofSize(size).withPage(page));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicoResponse> findById(@PathVariable Long id) {
        TopicoResponse response = topicoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicoResponse> update(@PathVariable Long id, @RequestBody @Valid TopicoRequest request) {
        TopicoResponse response = topicoService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}




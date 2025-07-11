package br.com.forumhub.aluraOne.security;

import br.com.forumhub.aluraOne.exceptions.UsuarioNaoEncontradoException;
import br.com.forumhub.aluraOne.model.Usuario;
import br.com.forumhub.aluraOne.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com o email: " + email));

        return new User(usuario.getEmail(), usuario.getSenha(), Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        ));
    }
}

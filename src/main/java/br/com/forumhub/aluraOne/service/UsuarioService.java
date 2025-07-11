package br.com.forumhub.aluraOne.service;

import br.com.forumhub.aluraOne.dto.UsuarioRequestLogin;
import br.com.forumhub.aluraOne.dto.UsuarioRequestRegistro;
import br.com.forumhub.aluraOne.dto.UsuarioResponseLogin;
import br.com.forumhub.aluraOne.dto.UsuarioResponseRegistro;
import br.com.forumhub.aluraOne.exceptions.EmailExistenteException;
import br.com.forumhub.aluraOne.exceptions.SenhaIncorretaException;
import br.com.forumhub.aluraOne.exceptions.UsuarioNaoEncontradoException;
import br.com.forumhub.aluraOne.model.Usuario;
import br.com.forumhub.aluraOne.repository.UsuarioRepository;
import br.com.forumhub.aluraOne.security.CustomUserDetailsService;
import br.com.forumhub.aluraOne.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final CustomUserDetailsService detailsService;
    private final JwtTokenProvider provider;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder, AuthenticationManager manager,
                          CustomUserDetailsService detailsService, JwtTokenProvider provider) {
        this.repository = repository;
        this.encoder = encoder;
        this.manager = manager;
        this.detailsService = detailsService;
        this.provider = provider;
    }

    @Transactional
    public UsuarioResponseRegistro save(UsuarioRequestRegistro request){
        if (repository.findByEmail(request.email()).isPresent()){
            throw new EmailExistenteException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setSenha(encoder.encode(request.senha()));
        usuario.setDataCadastro(LocalDateTime.now());

        Usuario savedUsuario = repository.save(usuario);
        return new UsuarioResponseRegistro(savedUsuario.getNome(), savedUsuario.getEmail());
    }

    public UsuarioResponseLogin login (UsuarioRequestLogin request){
        Usuario usuario = repository.findByEmail(request.email()).orElseThrow(() -> new UsuarioNaoEncontradoException(
                "Usuário não encontrado com o email: " + request.email()
        ));

        if (!encoder.matches(request.senha(), usuario.getSenha())){
            throw new SenhaIncorretaException("Senha incorreta. Verifique suas credenciais e tente novamente.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usuario.getEmail(), request.senha()
        );

        Authentication authentication = manager.authenticate(authenticationToken);
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String token = provider.generateToken(details);

        return new UsuarioResponseLogin(token);
    }
}

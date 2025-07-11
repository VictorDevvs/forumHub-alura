package br.com.forumhub.aluraOne.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService detailsService;

public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService detailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);

            try {
                if (jwtTokenProvider.validateToken(token)){
                    String email = jwtTokenProvider.getEmailFromToken(token);

                    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                        var userDetails = detailsService.loadUserByUsername(email);

                        var authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            }
        }

        filterChain.doFilter(request, response);
    }
}

package br.com.mysterious.mysteriousapi.infrastructure.security;

import br.com.mysterious.mysteriousapi.application.providers.TokenProvider;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUserType;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    MysteriousUserRepository mysteriousUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (!request.getRequestURI().contains("/auth/user/signIn")) {
            System.out.println("ROTA ATUAL: " + request.getRequestURI());
            if (token != null) {
                var login = tokenProvider.validateToken(token);

                if (login != null) {
                    Optional<MysteriousUser> user = mysteriousUserRepository.findByEmail(login);
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                    if (user.get().getMysteriousUserType() == MysteriousUserType.ADMINISTRATOR) {
                        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
                    }
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        // Retorna null se o token não foi enviado
        return null;
    }
}

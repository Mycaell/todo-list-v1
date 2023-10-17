package com.studies.todolist.infrastructure.persistence.security.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.studies.todolist.infrastructure.persistence.repository.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class TaskFilter extends OncePerRequestFilter {

    private final IUserRepository userRepository;

    public TaskFilter(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks")) {

            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            var user = this.userRepository.findByUsername(username);

            if (user.isPresent()) {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.get().getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("user", user.get());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            } else {
                response.sendError(401, "Usuário sem autorização");
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }

}

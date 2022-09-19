package DevNation.PetNation.Security;

import DevNation.PetNation.Security.Services.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthByTokenFilter extends OncePerRequestFilter {

    public AuthByTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){

            return null;

        } else return token.substring(7, token.length());

    }
}

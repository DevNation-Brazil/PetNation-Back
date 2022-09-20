package DevNation.PetNation.Security;

import DevNation.PetNation.Security.Models.User;
import DevNation.PetNation.Security.Repositories.UserRepository;
import DevNation.PetNation.Security.Services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository userRepository;

    public AuthByTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        
        if (valido){
            autenticarUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUser(String token) {

        Integer idUser = tokenService.getIdUser(token);

        User user = userRepository.findById(idUser).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){

            return null;

        } else return token.substring(7, token.length());

    }
}

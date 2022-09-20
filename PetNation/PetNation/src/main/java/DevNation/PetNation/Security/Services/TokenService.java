package DevNation.PetNation.Security.Services;

import DevNation.PetNation.Security.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.jwt.expiration}")
    private String expiration;

    @Value("${api.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication){

        User userLogado = (User) authentication.getPrincipal();

        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("API").setSubject(userLogado.getId().toString()).setIssuedAt(hoje)
                .setExpiration(expiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String token) {

        try {

            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

            return true;

        } catch (Exception e){

            return false;
        }
    }

    public Integer getIdUser(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}

package DevNation.PetNation.Security.Controllers;

import DevNation.PetNation.Security.Models.TokenDTO;
import DevNation.PetNation.Security.Models.User;
import DevNation.PetNation.Security.Models.UserDTO;
import DevNation.PetNation.Security.Repositories.UserRepository;
import DevNation.PetNation.Security.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody UserDTO userDTO){

        UsernamePasswordAuthenticationToken dados = userDTO.converter();

        try {

            Authentication authentication = authenticationManager.authenticate(dados);

            String token = tokenService.gerarToken(authentication);

            Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
            String nome = user.get().getNome();

            return ResponseEntity.ok(new TokenDTO(token, "Bearer", userDTO.getEmail(), nome));

        } catch (AuthenticationException e){

            return ResponseEntity.badRequest().build();
        }

    }
}

package DevNation.PetNation.Security.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenDTO {

    private String token;

    private String tipo;
}

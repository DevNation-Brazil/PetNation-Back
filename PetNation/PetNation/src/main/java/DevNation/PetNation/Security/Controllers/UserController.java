package DevNation.PetNation.Security.Controllers;

import DevNation.PetNation.Models.Pet;
import DevNation.PetNation.Security.Models.User;
import DevNation.PetNation.Security.Models.UserDTO;
import DevNation.PetNation.Security.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> cadastrarUser(@RequestBody UserDTO userDTO) {

        try {

            User userCadastrado = userService.cadastrarUser(userDTO);

            if(!Objects.isNull(userCadastrado)){

                return ResponseEntity.status(HttpStatus.OK).body(userCadastrado);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Usuário inválido");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

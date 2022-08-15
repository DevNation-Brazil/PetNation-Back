package DevNation.PetNation.Controllers;

import DevNation.PetNation.Models.Pet;
import DevNation.PetNation.Services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<?> cadastrarPet(@RequestBody Pet novoPet) {

        try {

            Pet petCadastrado = petService.cadastrarPet(novoPet);

            if(!Objects.isNull(petCadastrado)){

                return ResponseEntity.status(HttpStatus.OK).body(petCadastrado);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {

        try {

            List<Pet> petsCadastrados = petService.listarTodos();

            if(!petsCadastrados.isEmpty()){

                return ResponseEntity.status(HttpStatus.OK).body(petsCadastrados);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Lista vazia");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}


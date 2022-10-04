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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PatchMapping("/{petId}")
    public ResponseEntity<?> editarPet(@PathVariable Integer petId, @RequestBody Pet pet) {

        try {

            Pet petEditado = petService.editarPet(petId, pet);

            if(!Objects.isNull(petEditado)){

                return ResponseEntity.status(HttpStatus.OK).body(petEditado);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> removerPet(@PathVariable Integer petId) {

        try {

            boolean removido = petService.removerPet(petId);

            if(removido){

                return ResponseEntity.status(HttpStatus.OK).body(removido);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{petId}")
    public ResponseEntity<?> listarPet(@PathVariable Integer petId) {

        try {

            Pet petsCadastrado = petService.listarPet(petId);

            if(!Objects.isNull(petsCadastrado)){

                return ResponseEntity.status(HttpStatus.OK).body(petsCadastrado);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Id inválido");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}


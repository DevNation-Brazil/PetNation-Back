package DevNation.PetNation.Controllers;

import DevNation.PetNation.Models.Pet;
import DevNation.PetNation.Services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/pet")
    public ResponseEntity<?> cadastrarPet(@RequestParam(value = "file", required = false) MultipartFile file, @RequestPart("DTO") Pet novoPet) throws IOException {

        if (!Objects.isNull(file)) {

            Pet petCadastrado = petService.cadastrarPetComImagem(novoPet, file);

            try {

                if (!Objects.isNull(petCadastrado)) {

                    return ResponseEntity.status(HttpStatus.OK).body(petCadastrado);

                } else {

                    return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
                }
            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }

        } else {

            Pet petCadastrado = petService.cadastrarPetSemImagem(novoPet);

            try {

                if (!Objects.isNull(petCadastrado)) {

                    return ResponseEntity.status(HttpStatus.OK).body(petCadastrado);

                } else {

                    return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
                }
            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }




    @GetMapping("/key/pet")
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

    @PatchMapping("/pet/{petId}")
    public ResponseEntity<?> editarPet(@PathVariable Integer petId, @RequestParam(value = "file", required = false) MultipartFile file, @RequestPart("DTO") Pet pet) {

        if (!Objects.isNull(file)) {

            try {

                Pet petEditado = petService.editarPet(petId, pet, file);

                if(!Objects.isNull(petEditado)){

                    return ResponseEntity.status(HttpStatus.OK).body(petEditado);

                } else {

                    return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
                }
            } catch (Exception e){

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }

        } else {

            try {

                Pet petEditado = petService.editarPetSemImagem(petId, pet);

                if(!Objects.isNull(petEditado)){

                    return ResponseEntity.status(HttpStatus.OK).body(petEditado);

                } else {

                    return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Pet inválido");
                }
            } catch (Exception e){

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }



    @DeleteMapping("/pet/{petId}")
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

    @GetMapping("/pet/{petId}")
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


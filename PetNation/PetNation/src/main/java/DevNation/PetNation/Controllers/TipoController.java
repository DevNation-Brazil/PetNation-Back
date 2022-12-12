package DevNation.PetNation.Controllers;

import DevNation.PetNation.Models.Raca;
import DevNation.PetNation.Models.Tipo;
import DevNation.PetNation.Services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/key/tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {

        try {

            List<Tipo> tiposCadastrados = tipoService.listarTipos();

            if(!tiposCadastrados.isEmpty()){

                return ResponseEntity.status(HttpStatus.OK).body(tiposCadastrados);

            } else {

                return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Lista vazia");
            }
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

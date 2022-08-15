package DevNation.PetNation.Services;

import DevNation.PetNation.Models.Tipo;
import DevNation.PetNation.Repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    public List<Tipo> listarTipos(){
        return tipoRepository.findAll();
    }
}

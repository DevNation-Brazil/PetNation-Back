package DevNation.PetNation.Services;

import DevNation.PetNation.Models.Raca;
import DevNation.PetNation.Repositories.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacaService {

    @Autowired
    private RacaRepository racaRepository;

    public List<Raca> listarRacas(){
        return racaRepository.findAll();
    }
}

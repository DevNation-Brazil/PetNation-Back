package DevNation.PetNation.Services;

import DevNation.PetNation.Models.Pet;
import DevNation.PetNation.Repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;


    public Pet cadastrarPet(Pet novoPet){

        if(!Objects.isNull(novoPet)){

            petRepository.save(novoPet);
            return novoPet;

        } else return null;
    }

    public List<Pet> listarTodos(){
        return petRepository.findAll();
    }
}

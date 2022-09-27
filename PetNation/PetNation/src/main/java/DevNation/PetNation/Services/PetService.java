package DevNation.PetNation.Services;

import DevNation.PetNation.Models.Pet;
import DevNation.PetNation.Repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


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

    public Pet editarPet(Integer id, Pet pet) {

        if(!Objects.isNull(pet) && id!= null){

            Pet petParaEditar = petRepository.findById(id).orElse(null);

            petParaEditar.setNome(pet.getNome());
            petParaEditar.setIdade(pet.getIdade());
            petParaEditar.setPorte(pet.getPorte());
            petParaEditar.setSexo(pet.getSexo());
            petParaEditar.setRaca(pet.getRaca());
            petParaEditar.setTipo(pet.getTipo());

            petRepository.save(petParaEditar);
            return petParaEditar;

        } return null;
    }

    public boolean removerPet(Integer id){

        petRepository.deleteById(id);

        return true;
    }
}

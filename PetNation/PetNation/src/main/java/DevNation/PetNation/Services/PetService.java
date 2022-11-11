package DevNation.PetNation.Services;

import DevNation.PetNation.Models.Pet;
import DevNation.PetNation.Repositories.PetRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;


    public Pet cadastrarPetComImagem(Pet novoPet, MultipartFile file) throws IOException {

        if(!Objects.isNull(novoPet)){

            byte[] image = file.getBytes();
            String hash = DigestUtils.md5Hex(image);

            novoPet.setPetImageHash(hash);

            String dir = System.getProperty("user.dir");

            file.transferTo(new File(dir + "\\Back End\\PetNation\\PetNation\\src\\main\\java\\DevNation\\PetNation\\Content\\Images\\" + hash + ".jpg"));
            novoPet.setImageSource("http://localhost:8080/content/image/" + hash + ".jpg");

            petRepository.save(novoPet);
            return novoPet;

        } else return null;
    }

    public Pet cadastrarPetSemImagem(Pet novoPet) throws IOException {

        if(!Objects.isNull(novoPet)){

            petRepository.save(novoPet);

            return novoPet;

        } else return null;
    }

    public List<Pet> listarTodos(){
        return petRepository.findAll();
    }

    public Pet listarPet(Integer id){
        return petRepository.findById(id).orElse(null);
    }

    public Pet editarPet(Integer id, Pet pet, MultipartFile file) throws IOException {

        if(!Objects.isNull(pet) && id!= null){

            String dir = System.getProperty("user.dir");

            Pet petParaEditar = petRepository.findById(id).orElse(null);

            String oldHash = petParaEditar.getPetImageHash();

            File oldFile = (new File(dir + "\\Back End\\PetNation\\PetNation\\src\\main\\java\\DevNation\\PetNation\\Content\\Images\\" + oldHash + ".jpg"));
            oldFile.delete();

            byte[] image = file.getBytes();
            String hash = DigestUtils.md5Hex(image);

            petParaEditar.setPetImageHash(hash);
            petParaEditar.setNome(pet.getNome());
            petParaEditar.setIdade(pet.getIdade());
            petParaEditar.setPorte(pet.getPorte());
            petParaEditar.setSexo(pet.getSexo());
            petParaEditar.setRaca(pet.getRaca());
            petParaEditar.setTipo(pet.getTipo());

            file.transferTo(new File(dir + "\\Back End\\PetNation\\PetNation\\src\\main\\java\\DevNation\\PetNation\\Content\\Images\\" + hash + ".jpg"));
            petParaEditar.setImageSource("http://localhost:8080/content/image/" + hash + ".jpg");

            petRepository.save(petParaEditar);
            return petParaEditar;

        } return null;
    }

    public Pet editarPetSemImagem(Integer id, Pet pet) throws IOException {

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

        Pet petBase = petRepository.findById(id).orElse(null);

        if(!Objects.isNull(petBase)){

            String hash = petBase.getPetImageHash();

            String dir = System.getProperty("user.dir");

            File file = (new File(dir + "\\Back End\\PetNation\\PetNation\\src\\main\\java\\DevNation\\PetNation\\Content\\Images\\" + hash + ".jpg"));
            file.delete();

            petRepository.deleteById(id);

            return true;

        } else return false;
    }
}

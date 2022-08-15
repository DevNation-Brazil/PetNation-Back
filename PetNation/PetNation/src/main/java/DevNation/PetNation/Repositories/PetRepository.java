package DevNation.PetNation.Repositories;

import DevNation.PetNation.Models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}

package DevNation.PetNation.Repositories;

import DevNation.PetNation.Models.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaRepository extends JpaRepository<Raca, String> {
}

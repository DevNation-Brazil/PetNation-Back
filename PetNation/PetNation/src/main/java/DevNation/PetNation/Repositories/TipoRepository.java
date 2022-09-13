package DevNation.PetNation.Repositories;

import DevNation.PetNation.Models.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, String> {
}

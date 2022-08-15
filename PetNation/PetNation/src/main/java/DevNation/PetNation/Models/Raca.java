package DevNation.PetNation.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Raca {

    @Id
    private String nome;

    @ManyToOne
    @JoinColumn(name = "Tipo")
    private Tipo tipo;
}

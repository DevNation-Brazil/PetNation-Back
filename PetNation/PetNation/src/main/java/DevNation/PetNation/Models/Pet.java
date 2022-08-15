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
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String idade;

    private String sexo;

    private String porte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tipo")
    private Tipo tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Raça")
    private Raca raca;
}

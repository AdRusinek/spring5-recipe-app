package rusinek.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // In this one we don't have to specify cascade because this instance we are going to allow
    // the recipe to own this.  So if we delete the Notes object  we don't want to go back and delete
    // the recipe object / inverse if we want to delete recipe, of course we want to delete the recipe notes.
    @OneToOne
    private Recipe recipe;

    // doing this against String JPA is going to expect a distort in a clob field int the DB (This annotation
    // allow us to use String that a lot bigger than 255 characters )
    @Lob
    private String recipeNotes;


}

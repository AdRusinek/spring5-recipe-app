package rusinek.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

// lombok includes getter/setter/hashcode/equals/to string/req args constructor
@Data
@EqualsAndHashCode(exclude = {"recipes"}) // this will tell to exclude from equals and hashcode the recipes property
// and I needed to that on other entities.
// without this there was an error because of bidirectional relationship
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    // mappedBy it is going to be on the joinTable from that sets( from the other side of the relationship)
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}

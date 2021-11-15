package recipes.businesslayer.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private int id;

    private String name;

    private String category;

    @Column(name = "last_change_date")
    private LocalDateTime lastChangeDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "author_email")
    private User author;

    @ElementCollection
    @OrderColumn
    private List<String> ingredients;

    @ElementCollection
    @OrderColumn
    private List<String> directions;

}

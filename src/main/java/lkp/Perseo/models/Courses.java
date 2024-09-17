package lkp.Perseo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinTable(
            name = "courses_user", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "courses_id"), // Columna de esta entidad
            inverseJoinColumns = @JoinColumn(name = "user_id") // Columna de la otra entidad
    )


    @JoinColumn(name = "idUser", nullable = false)
    @JsonBackReference
    private User user;

}

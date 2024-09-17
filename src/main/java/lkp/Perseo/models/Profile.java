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
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id profile")
    private Long idProfile;

    @Column(name = "company")
    private String company;

   @Column(name = "year")
    private int year;

    @Column(name = "duration")
    private int duration;

    @Column(name = "experience")
    private String experience;

   @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    @JsonBackReference
    private User user;

}

package be.bt.cinemasnoussapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String filmDirector;
    private Date releaseDate;
    private double duration;
    private String picture;
    @OneToMany(mappedBy = "movie")
    private Collection<FilmScreening> projections;
    @ManyToOne
    private Category category;
}

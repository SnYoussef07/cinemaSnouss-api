package be.bt.cinemasnoussapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@JsonIgnoreProperties(value = {"projections"})
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
    private String banner;
    private String trailer;
    private String picture;
    @OneToMany(mappedBy = "movie")
    private Collection<FilmScreening> projections;
    @ManyToOne
    @JsonIgnore
    private Category category;
}

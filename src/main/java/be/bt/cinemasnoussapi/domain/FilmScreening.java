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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmScreening implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date screeningDate;
    private double price;
    @ManyToOne
    @JsonIgnore
    private Room room;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private SessionMovie sessionMovie;
    @OneToMany(mappedBy = "filmScreening")
    private Collection<Ticket> tickets;

}

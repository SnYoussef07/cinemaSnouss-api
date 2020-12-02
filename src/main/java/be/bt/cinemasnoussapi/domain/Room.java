package be.bt.cinemasnoussapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@JsonIgnoreProperties(value = {"seats"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int numberOfSeat;
    @OneToMany(mappedBy = "room")
    private Collection<Seat> seats;
    @OneToMany(mappedBy = "room")
    private Collection<FilmScreening> projections;
}

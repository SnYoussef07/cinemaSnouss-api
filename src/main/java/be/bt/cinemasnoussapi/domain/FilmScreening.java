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
public class FilmScreening implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date screeningDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    private double price;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Movie movie;
    @OneToMany(mappedBy = "filmScreening")
    private Collection<Ticket> tickets;
}

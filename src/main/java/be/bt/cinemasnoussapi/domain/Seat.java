package be.bt.cinemasnoussapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seat implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private int number;
    @ManyToOne
    private Room room;
    @OneToMany(mappedBy = "seat")
    private Collection<Ticket> tickets;


}

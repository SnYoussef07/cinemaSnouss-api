package be.bt.cinemasnoussapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private double price;
    @Column(unique = true, nullable = true)
    private Integer paymentCode;
    private boolean reserve;
    @ManyToOne
    private Seat seat;
    @ManyToOne
    @JsonIgnore
    private FilmScreening filmScreening;
}

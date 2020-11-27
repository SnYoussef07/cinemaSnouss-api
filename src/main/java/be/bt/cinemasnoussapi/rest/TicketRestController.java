package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.FilmScreening;
import be.bt.cinemasnoussapi.domain.Room;
import be.bt.cinemasnoussapi.domain.Seat;
import be.bt.cinemasnoussapi.domain.Ticket;
import be.bt.cinemasnoussapi.repository.ITicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
@CrossOrigin
public class TicketRestController {

    private ITicketRepository ticketRepository;

    public TicketRestController(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable("id") Long id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Ticket>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/seat")
    public ResponseEntity<Seat> getSeatFromTicket(@PathVariable("id") Long id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Seat>(result.get().getSeat(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/filmScreening")
    public ResponseEntity<FilmScreening> getFilmScreeningFromTicket(@PathVariable("id") Long id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<FilmScreening>(result.get().getFilmScreening(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        Optional<Ticket> result = ticketRepository.findById(ticket.getId());
        if (!result.isPresent()) {
            ticketRepository.save(ticket);
            return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket) {
        Optional<Ticket> result = ticketRepository.findById(ticket.getId());
        if (result.isPresent()) {
            ticketRepository.save(ticket);
            return new ResponseEntity<Ticket>(ticket, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Ticket> deleteById(@PathVariable("id") Long id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if (result.isPresent()) {
            ticketRepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

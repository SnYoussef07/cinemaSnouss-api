package be.bt.cinemasnoussapi.rest;

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

    @PostMapping
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket room) {
        Optional<Ticket> result = ticketRepository.findById(room.getId());
        if (!result.isPresent()) {
            ticketRepository.save(room);
            return new ResponseEntity<Ticket>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Ticket> update(@RequestBody Ticket room) {
        Optional<Ticket> result = ticketRepository.findById(room.getId());
        if (result.isPresent()) {
            ticketRepository.save(room);
            return new ResponseEntity<Ticket>(room, HttpStatus.ACCEPTED);
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

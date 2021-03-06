package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.*;
import be.bt.cinemasnoussapi.repository.ISeatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
@CrossOrigin
public class SeatRestController {

    private ISeatRepository seatRepository;

    public SeatRestController(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Seat> findById(@PathVariable("id") Long id) {
        Optional<Seat> result = seatRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Seat>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/tickets")
    public ResponseEntity<Collection<Ticket>> getAllTicketFromSeat(@PathVariable("id") Long id) {
        Optional<Seat> result = seatRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get().getTickets(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/room")
    public ResponseEntity<Room> getRoomFromSeat(@PathVariable("id") Long id) {
        Optional<Seat> result = seatRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Room>(result.get().getRoom(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat) {
        Optional<Seat> result = seatRepository.findById(seat.getId());
        if (!result.isPresent()) {
            seatRepository.save(seat);
            return new ResponseEntity<Seat>(seat, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Seat> update(@RequestBody Seat seat) {
        Optional<Seat> result = seatRepository.findById(seat.getId());
        if (result.isPresent()) {
            seatRepository.save(seat);
            return new ResponseEntity<Seat>(seat, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Seat> deleteById(@PathVariable("id") Long id) {
        Optional<Seat> result = seatRepository.findById(id);
        if (result.isPresent()) {
            seatRepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


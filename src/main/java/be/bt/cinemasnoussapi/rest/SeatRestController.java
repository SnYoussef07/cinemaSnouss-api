package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.Seat;
import be.bt.cinemasnoussapi.repository.ISeatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat room) {
        Optional<Seat> result = seatRepository.findById(room.getId());
        if (!result.isPresent()) {
            seatRepository.save(room);
            return new ResponseEntity<Seat>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Seat> update(@RequestBody Seat room) {
        Optional<Seat> result = seatRepository.findById(room.getId());
        if (result.isPresent()) {
            seatRepository.save(room);
            return new ResponseEntity<Seat>(room, HttpStatus.ACCEPTED);
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


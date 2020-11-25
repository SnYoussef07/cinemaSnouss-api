package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.SessionMovie;
import be.bt.cinemasnoussapi.repository.ISessionMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessionMovie")
@CrossOrigin
public class SessionMovieRestController {

    private ISessionMovieRepository sessionMovie;

    public SessionMovieRestController(ISessionMovieRepository sessionMovie) {
        this.sessionMovie = sessionMovie;
    }

    @GetMapping
    public List<SessionMovie> getAllSessionMovies() {
        return sessionMovie.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SessionMovie> findById(@PathVariable("id") Long id) {
        Optional<SessionMovie> result = sessionMovie.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<SessionMovie>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<SessionMovie> addSessionMovie(@RequestBody SessionMovie room) {
        Optional<SessionMovie> result = sessionMovie.findById(room.getId());
        if (!result.isPresent()) {
            sessionMovie.save(room);
            return new ResponseEntity<SessionMovie>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<SessionMovie> update(@RequestBody SessionMovie room) {
        Optional<SessionMovie> result = sessionMovie.findById(room.getId());
        if (result.isPresent()) {
            sessionMovie.save(room);
            return new ResponseEntity<SessionMovie>(room, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SessionMovie> deleteById(@PathVariable("id") Long id) {
        Optional<SessionMovie> result = sessionMovie.findById(id);
        if (result.isPresent()) {
            sessionMovie.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

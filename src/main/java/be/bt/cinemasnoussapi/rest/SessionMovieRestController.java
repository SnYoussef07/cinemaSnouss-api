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

    private ISessionMovieRepository sessionMovieRepository;

    public SessionMovieRestController(ISessionMovieRepository sessionMovieRepository) {
        this.sessionMovieRepository = sessionMovieRepository;
    }

    @GetMapping
    public List<SessionMovie> getAllSessionMovies() {
        return sessionMovieRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SessionMovie> findById(@PathVariable("id") Long id) {
        Optional<SessionMovie> result = sessionMovieRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<SessionMovie>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<SessionMovie> addSessionMovie(@RequestBody SessionMovie sessionMovie) {
        Optional<SessionMovie> result = sessionMovieRepository.findById(sessionMovie.getId());
        if (!result.isPresent()) {
            sessionMovieRepository.save(sessionMovie);
            return new ResponseEntity<SessionMovie>(sessionMovie, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<SessionMovie> update(@RequestBody SessionMovie sessionMovie) {
        Optional<SessionMovie> result = sessionMovieRepository.findById(sessionMovie.getId());
        if (result.isPresent()) {
            sessionMovieRepository.save(sessionMovie);
            return new ResponseEntity<SessionMovie>(sessionMovie, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SessionMovie> deleteById(@PathVariable("id") Long id) {
        Optional<SessionMovie> result = sessionMovieRepository.findById(id);
        if (result.isPresent()) {
            sessionMovieRepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.Movie;
import be.bt.cinemasnoussapi.repository.IMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieRestController {

    private IMovieRepository movieRepository;

    public MovieRestController(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Movie>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie room) {
        Optional<Movie> result = movieRepository.findById(room.getId());
        if (!result.isPresent()) {
            movieRepository.save(room);
            return new ResponseEntity<Movie>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Movie> update(@RequestBody Movie room) {
        Optional<Movie> result = movieRepository.findById(room.getId());
        if (result.isPresent()) {
            movieRepository.save(room);
            return new ResponseEntity<Movie>(room, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable("id") Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            movieRepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

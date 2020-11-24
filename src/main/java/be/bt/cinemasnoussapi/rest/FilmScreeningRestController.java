package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.FilmScreening;
import be.bt.cinemasnoussapi.repository.IFilmScreeningRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmScreenings")
@CrossOrigin
public class FilmScreeningRestController {

    private IFilmScreeningRepository filmScreeningRepository;

    public FilmScreeningRestController(IFilmScreeningRepository filmScreeningRepository) {
        this.filmScreeningRepository = filmScreeningRepository;
    }

    @GetMapping
    public List<FilmScreening> getAllFilmScreenings() {
        return filmScreeningRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmScreening> findById(@PathVariable("id") Long id) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<FilmScreening>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<FilmScreening> addFilmScreening(@RequestBody FilmScreening room) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(room.getId());
        if (!result.isPresent()) {
            filmScreeningRepository.save(room);
            return new ResponseEntity<FilmScreening>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<FilmScreening> update(@RequestBody FilmScreening room) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(room.getId());
        if (result.isPresent()) {
            filmScreeningRepository.save(room);
            return new ResponseEntity<FilmScreening>(room, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<FilmScreening> deleteById(@PathVariable("id") Long id) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(id);
        if (result.isPresent()) {
            filmScreeningRepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

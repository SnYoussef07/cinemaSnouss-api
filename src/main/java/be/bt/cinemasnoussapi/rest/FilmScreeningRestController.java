package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.*;
import be.bt.cinemasnoussapi.repository.IFilmScreeningRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @GetMapping(path = "/{id}/tickets")
    public ResponseEntity<Collection<Ticket>> getAllTicketsFromFilmScreen(@PathVariable("id") Long id) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Collection<Ticket>>(result.get().getTickets(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/room")
    public ResponseEntity<Room> getRoomFromFilmScreen(@PathVariable("id") Long id) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Room>(result.get().getRoom(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/movie")
    public ResponseEntity<Movie> getMovieFromFilmScreen(@PathVariable("id") Long id) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Movie>(result.get().getMovie(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/sessionMovie")
    public ResponseEntity<SessionMovie> getSessionMovieFromFilmScreen(@PathVariable("id") Long id) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<SessionMovie>(result.get().getSessionMovie(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<FilmScreening> addFilmScreening(@RequestBody FilmScreening filmScreening) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(filmScreening.getId());
        if (!result.isPresent()) {
            filmScreeningRepository.save(filmScreening);
            return new ResponseEntity<FilmScreening>(filmScreening, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<FilmScreening> update(@RequestBody FilmScreening filmScreening) {
        Optional<FilmScreening> result = filmScreeningRepository.findById(filmScreening.getId());
        if (result.isPresent()) {
            filmScreeningRepository.save(filmScreening);
            return new ResponseEntity<FilmScreening>(filmScreening, HttpStatus.ACCEPTED);
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

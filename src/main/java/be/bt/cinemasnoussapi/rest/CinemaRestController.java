package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.Cinema;
import be.bt.cinemasnoussapi.repository.ICinemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cinemas")
@CrossOrigin
public class CinemaRestController {

    private ICinemaRepository cinemaRepository;

    public CinemaRestController(ICinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cinema> addCinema(@RequestBody Cinema cinema) {
        Optional<Cinema> result = cinemaRepository.findById(cinema.getId());
        if (!result.isPresent()) {
            cinemaRepository.save(cinema);
            return new ResponseEntity<Cinema>(cinema, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Cinema>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

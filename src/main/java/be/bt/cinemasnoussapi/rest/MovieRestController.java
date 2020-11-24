package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.IMovieRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieRestController {

    private IMovieRepository movieRepository;

    public MovieRestController(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}

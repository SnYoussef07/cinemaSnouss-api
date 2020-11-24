package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.IFilmScreeningRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmScreenings")
@CrossOrigin
public class FilmScreeningRestController {

    private IFilmScreeningRepository filmScreeningRepository;

    public FilmScreeningRestController(IFilmScreeningRepository filmScreeningRepository) {
        this.filmScreeningRepository = filmScreeningRepository;
    }
}

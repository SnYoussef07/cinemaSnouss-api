package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.*;
import be.bt.cinemasnoussapi.domain.FilmScreening;
import be.bt.cinemasnoussapi.repository.IMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
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

    @GetMapping(path = "/pictures/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPictures(@PathVariable("id") Long id) throws Exception {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(Files.readAllBytes(Paths.get
                    (System.getProperty("user.home") + "/cinesnoussimages/" + result.get().getPicture() + ".png")), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/filmScreenings")
    public ResponseEntity<Collection<FilmScreening>> getAllFilmScreenFromMovie(@PathVariable("id") Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Collection<FilmScreening>>(result.get().getProjections(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/category")
    public ResponseEntity<Category> getCategoryFromMovie(@PathVariable("id") Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Category>(result.get().getCategory(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Optional<Movie> result = movieRepository.findById(movie.getId());
        if (!result.isPresent()) {
            movieRepository.save(movie);
            return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public void uploadPicture(MultipartFile file, @PathVariable Long id) throws Exception {
        Optional<Movie> result = movieRepository.findById(id);
        result.get().setPicture(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/cinesnoussimages/" + result.get().getPicture() + ".png"), file.getBytes());
        movieRepository.save(result.get());
    }

    @PutMapping
    public ResponseEntity<Movie> update(@RequestBody Movie movie) {
        Optional<Movie> result = movieRepository.findById(movie.getId());
        if (result.isPresent()) {
            movieRepository.save(movie);
            return new ResponseEntity<Movie>(movie, HttpStatus.ACCEPTED);
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

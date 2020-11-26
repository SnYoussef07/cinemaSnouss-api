package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.Category;
import be.bt.cinemasnoussapi.domain.Movie;
import be.bt.cinemasnoussapi.repository.ICategoryrepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryRestController {

    private ICategoryrepository categoryrepository;

    public CategoryRestController(ICategoryrepository categoryrepository) {
        this.categoryrepository = categoryrepository;
    }

    @GetMapping
    public List<Category> getAllCategorys() {
        return categoryrepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        Optional<Category> result = categoryrepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Category>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{id}/movies")
    public ResponseEntity<Collection<Movie>> getAllMoviesFromCat(@PathVariable("id") Long id) {
        Optional<Category> result = categoryrepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Collection<Movie>>(result.get().getMovies(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category room) {
        Optional<Category> result = categoryrepository.findById(room.getId());
        if (!result.isPresent()) {
            categoryrepository.save(room);
            return new ResponseEntity<Category>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category room) {
        Optional<Category> result = categoryrepository.findById(room.getId());
        if (result.isPresent()) {
            categoryrepository.save(room);
            return new ResponseEntity<Category>(room, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable("id") Long id) {
        Optional<Category> result = categoryrepository.findById(id);
        if (result.isPresent()) {
            categoryrepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

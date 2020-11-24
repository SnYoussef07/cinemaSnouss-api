package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.ICategoryrepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryRestController {

    private ICategoryrepository categoryrepository;

    public CategoryRestController(ICategoryrepository categoryrepository) {
        this.categoryrepository = categoryrepository;
    }
}

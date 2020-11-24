package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.ISeatRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
@CrossOrigin
public class SeatRestController {

    private ISeatRepository seatRepository;

    public SeatRestController(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
}

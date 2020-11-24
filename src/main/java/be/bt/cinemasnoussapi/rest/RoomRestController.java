package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.IRoomRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomRestController {

    private IRoomRepository roomRepository;

    public RoomRestController(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}

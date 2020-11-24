package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.Room;
import be.bt.cinemasnoussapi.repository.IRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomRestController {

    private IRoomRepository roomRepository;

    public RoomRestController(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Room> findById(@PathVariable("id") Long id) {
        Optional<Room> result = roomRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<Room>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Optional<Room> result = roomRepository.findById(room.getId());
        if (!result.isPresent()) {
            roomRepository.save(room);
            return new ResponseEntity<Room>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    public ResponseEntity<Room> update(@RequestBody Room room) {
        Optional<Room> result = roomRepository.findById(room.getId());
        if (result.isPresent()) {
            roomRepository.save(room);
            return new ResponseEntity<Room>(room, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Room> deleteById(@PathVariable("id") Long id) {
        Optional<Room> result = roomRepository.findById(id);
        if (result.isPresent()) {
            roomRepository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

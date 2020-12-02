package be.bt.cinemasnoussapi;

import be.bt.cinemasnoussapi.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaSnoussApiApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService cinemaInitService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaSnoussApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*cinemaInitService.initRooms();
        cinemaInitService.initSeats();
        cinemaInitService.initSessionMovie();
        cinemaInitService.initCategories();
        cinemaInitService.initMovies();
        cinemaInitService.initFilmScreening();
        cinemaInitService.initTickets();*/
    }
}

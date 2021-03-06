package be.bt.cinemasnoussapi.service;

import be.bt.cinemasnoussapi.domain.*;
import be.bt.cinemasnoussapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitIImpl implements ICinemaInitService {

    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private ISessionMovieRepository iSessionMovieRepository;
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private ICategoryrepository categoryrepository;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IFilmScreeningRepository filmScreeningRepository;
    @Autowired
    private ITicketRepository ticketRepository;


    @Override
    public void initRooms() {
        for (int i = 1; i < 9; i++) {
            Room room = new Room();
            room.setName("R" + i);
            room.setNumberOfSeat(150);
            roomRepository.save(room);
        }
    }


    @Override
    public void initSessionMovie() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
            SessionMovie sessionMovie = new SessionMovie();
            try {
                sessionMovie.setStartTime(dateFormat.parse(s));
                iSessionMovieRepository.save(sessionMovie);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initSeats() {
        roomRepository.findAll().forEach(room -> {
            for (int i = 0; i < room.getNumberOfSeat(); i++) {
                Seat seat = new Seat();
                seat.setNumber(i + 1);
                seat.setRoom(room);
                seatRepository.save(seat);
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire", "Actions", "Fiction", "Drama").forEach(cat -> {
            Category category = new Category();
            category.setName(cat);
            categoryrepository.save(category);
        });
    }

    @Override
    public void initMovies() {
        double[] duration = new double[]{1.0, 1.5, 2.0, 2.5, 3.0};
        List<Category> categories = categoryrepository.findAll();
        Stream.of("Avengers Endgame", "Forrest Gump", "Les Évadés", "Ip Man 2", "Justice League", "Inception",
                "Interstellar", "The Dark Knight", "La Légende d'Excalibur", "Sherlock Holmes").forEach(title -> {
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setDuration(duration[new Random().nextInt(duration.length)]);
            movie.setPicture(title.replace(" ", ""));
            movie.setBanner(title.replace(" ", "") + "Bn");
            movie.setCategory(categories.get(new Random().nextInt(categories.size())));
            movieRepository.save(movie);
        });
    }

    @Override
    public void initFilmScreening() {
        List<Movie> myMovies = movieRepository.findAll();
        roomRepository.findAll().forEach(room -> {
            int randomx = new Random().nextInt(myMovies.size());
            Movie movie = myMovies.get(randomx);
            iSessionMovieRepository.findAll().forEach(sessionMovie -> {
                FilmScreening filmScreening = new FilmScreening();
                filmScreening.setScreeningDate(new Date());
                filmScreening.setMovie(movie);
                myMovies.remove(movie);
                filmScreening.setPrice(12);
                filmScreening.setRoom(room);
                filmScreening.setSessionMovie(sessionMovie);
                filmScreeningRepository.save(filmScreening);
            });
        });
    }

    @Override
    public void initTickets() {
        filmScreeningRepository.findAll().forEach(screening -> {
            screening.getRoom().getSeats().forEach(seat -> {
                Ticket ticket = new Ticket();
                ticket.setSeat(seat);
                ticket.setPrice(screening.getPrice());
                ticket.setFilmScreening(screening);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });
    }
}

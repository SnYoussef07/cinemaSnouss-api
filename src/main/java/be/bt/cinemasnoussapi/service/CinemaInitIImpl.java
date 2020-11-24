package be.bt.cinemasnoussapi.service;

import be.bt.cinemasnoussapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaInitIImpl implements ICinemaInitService {

    @Autowired
    private IRoomRepository roomRepository;
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

    }

    @Override
    public void initSeats() {

    }

    @Override
    public void initCategories() {

    }

    @Override
    public void initMovies() {

    }

    @Override
    public void initFilmScreening() {

    }

    @Override
    public void initTickets() {

    }
}

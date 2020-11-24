package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.repository.ITicketRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@CrossOrigin
public class TicketRestController {

    private ITicketRepository ticketRepository;

    public TicketRestController(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}

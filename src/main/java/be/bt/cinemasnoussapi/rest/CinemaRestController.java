package be.bt.cinemasnoussapi.rest;

import be.bt.cinemasnoussapi.domain.Ticket;
import be.bt.cinemasnoussapi.repository.ITicketRepository;
import be.bt.cinemasnoussapi.service.TicketForm;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cinema")
@CrossOrigin
public class CinemaRestController {

    private ITicketRepository ticketRepository;

    public CinemaRestController(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/payTickets")
    public List<Ticket> payTickets(@RequestBody TicketForm ticketForm) {
        List<Ticket> listTickets = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setClientName(ticketForm.getNameClient());
            ticket.setReserve(true);
            //ticket.setPaymentCode(ticketForm.getPaymentCode());
            ticketRepository.save(ticket);
            listTickets.add(ticket);
        });
        return listTickets;
    }
}

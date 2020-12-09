package be.bt.cinemasnoussapi.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketForm {
    private String nameClient;
    private int paymentCode;
    private List<Long> ticketsId = new ArrayList<>();
}

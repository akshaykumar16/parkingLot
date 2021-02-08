package com.parkingLot.ticketing;

import com.parkingLot.enums.ParkingTicketStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class ParkingTicket {

    private String ticketNumber;

    private LocalDateTime issuedAt;

    private LocalDateTime payedAt;

    private double payedAmount;

    private ParkingTicketStatus parkingTicketStatus;

    public ParkingTicket()
    {
        this.issuedAt = LocalDateTime.now();
        this.ticketNumber = String.valueOf(10000 + new Random().nextInt(90000));
        this.parkingTicketStatus = ParkingTicketStatus.ACTIVE;
        this.payedAmount = 0.0;
    }

    public double calculateTheCharge()
    {
        this.payedAt = LocalDateTime.now();
        long parkHours = ChronoUnit.MINUTES.between(issuedAt, payedAt);
        payedAmount = parkHours < 2 ? 10 : (parkHours - 2) * 10 + 10;
        this.parkingTicketStatus = ParkingTicketStatus.PAID;
        return payedAmount;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
}

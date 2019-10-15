package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    public static final String NOT_ENOUGH_POSITION = "Not enough position.";
    public static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private List<ParkingLot> parkingLotList = new ArrayList<>();
    private ParkingLot parkingLot;
    private String lastErrorMessage;


    public void setParkingLot(ParkingLot parkingLot) {
        parkingLotList.add(parkingLot);
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket;
        ParkingLot fetchedParkingLot = parkingLotList.stream()
                                        .filter(parkingLot -> parkingLot.getAvailableParkingPosition() != 0)
                                        .findFirst()
                                        .orElse(null);

        if(fetchedParkingLot != null){
            ticket = fetchedParkingLot.park(car);
            return ticket;
        }else{
            ticket = null;
        }

        lastErrorMessage = NOT_ENOUGH_POSITION;
        return null;

    }

    public Car fetch(ParkingTicket ticket) {
        Car car = new Car();

        if(ticket == null) {
            lastErrorMessage = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            return null;
        }

        for(ParkingLot parkingLot : parkingLotList){
            car = parkingLot.fetch(ticket);

            if(car != null) {
                return car;
            }else{
                car = null;
            }
        }

        if(car == null){
            lastErrorMessage = UNRECOGNIZED_PARKING_TICKET;
            return null;
        }

        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }


}

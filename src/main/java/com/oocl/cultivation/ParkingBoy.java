package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    public static final String NOT_ENOUGH_POSITION = "Not enough position.";
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

        if(ticket == null){
            setLastErrorMessage(NOT_ENOUGH_POSITION);
            return null;
        }

        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = new Car();

        if(ticket == null) {
            lastErrorMessage = "Please provide your parking ticket";
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
            lastErrorMessage = "Unrecognized parking ticket.";
            return null;
        }

        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }


}

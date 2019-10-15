package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int CAPACITY = 10;
    private final int capacity;

    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public ParkingTicket park(Car car) {
        if(getAvailableParkingPosition() == 0){
            return null;
        }

        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);

        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
       Car car =  cars.get(ticket);
       cars.remove(ticket);

       return car;
    }


}

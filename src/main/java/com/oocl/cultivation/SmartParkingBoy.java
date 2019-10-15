package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{


    @Override
    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotList = getParkingLotList();

        ParkingTicket ticket;
        ParkingLot fetchedParkingLot = parkingLotList.stream()
                .reduce(this::getMoreSpaciousParkingLot)
                .orElse(null);

        if(fetchedParkingLot != null){
            ticket = fetchedParkingLot.park(car);
            return ticket;
        }else{
            ticket = null;
        }

        setLastErrorMessage(NOT_ENOUGH_POSITION);
        return null;

    }

    private ParkingLot getMoreSpaciousParkingLot(ParkingLot parkLotOne, ParkingLot parkLotTwo) {
        return Math.abs(parkLotTwo.getAvailableParkingPosition()) > Math.abs(parkLotOne.getAvailableParkingPosition())
                ? parkLotTwo : parkLotOne;
    }


}

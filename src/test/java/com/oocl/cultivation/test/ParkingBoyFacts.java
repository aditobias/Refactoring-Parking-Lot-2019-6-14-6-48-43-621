package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {

    @Test
    @DisplayName("STORY 1 AC1: should return parkingTicket when parkingBoy is given a car")
    void should_return_a_parking_ticket_when_parking_boy_given_a_car_into_parking_lot() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        parkingBoy.setParkingLot(parkingLot);

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    @DisplayName("STORY 1 AC1: should return car when given ticket")
    void should_return_car_when_parking_boy_is_given_ticket_to_fetch_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        parkingBoy.setParkingLot(parkingLot);

        ParkingTicket parkingTicket = parkingBoy.park(car);

        //When
        car = parkingBoy.fetch(parkingTicket);

        //Then
        assertNotNull(car);
    }

    @Test
    @DisplayName("STORY 1 AC2: should return correct car when given correct ticket")
    void should_return_correct_car_by_parking_boy_using_given_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setParkingLot(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car sportsCar = new Car();
        ParkingTicket parkingTicketSportsCar = parkingBoy.park(sportsCar);

        //When
        car = parkingBoy.fetch(parkingTicket);
        sportsCar = parkingBoy.fetch(parkingTicketSportsCar);
        //Then

        assertNotEquals(car.hashCode(), sportsCar.hashCode());
        assertNotSame(car, sportsCar);
    }

    @Test
    @DisplayName("STORY 1 AC3: should not fetch car if wrong parkingTicket is given")
    void should_not_fetch_car_if_wrong_parking_ticket_is_given() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car car;
        parkingBoy.setParkingLot(parkingLot);

        //When
        car = parkingBoy.fetch(wrongParkingTicket);

        //Then
        assertNull(car);
    }

    @Test
    @DisplayName("STORY 1 AC3: should not fetch car if no ticket is given")
    void should_not_fetch_car_if_no_ticket_is_given() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car;
        parkingBoy.setParkingLot(parkingLot);

        //When
        car = parkingBoy.fetch(null);

        //Then
        assertNull(car);
    }

    @Test
    @DisplayName("STORY 1 AC4: should not fetch car when ticket is already been used")
    void should_not_fetch_car_if_ticket_is_already_been_used() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setParkingLot(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //When
        car = parkingBoy.fetch(parkingTicket);
        car = parkingBoy.fetch(parkingTicket);

        //Then
        assertNull(car);
    }

    @Test
    @DisplayName("STORY 1 AC5: should not give parking ticket if parking lot is already full")
    void should_not_park_ticket_if_parking_lot_is_already_full() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket finalParkingTicket = new ParkingTicket();
        parkingBoy.setParkingLot(parkingLot);

        //When
        Car finalCar = new Car();
        for(int i = 0; i <= 21; i++){
            finalParkingTicket = parkingBoy.park(finalCar);
        }

        //Then
        assertNull(finalParkingTicket);
    }

    @Test
    @DisplayName("STORY 2 AC1: should return Unrecognized Parking Ticket message if wrong ticket is given")
    void should_return_unrecognized_parking_ticket_message_if_wrong_ticket_is_given() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car;
        parkingBoy.setParkingLot(parkingLot);


        //When
        car = parkingBoy.fetch(new ParkingTicket());

        //Then
        assertNull(car);
        assertEquals(parkingBoy.getLastErrorMessage(), "Unrecognized parking ticket.");

    }

    @Test
    @DisplayName("STORY 2 AC2: should return Please provide your parking ticket message if ticket is not given")
    void should_return_please_provide_your_parking_ticket_message_if_ticket_is_not_given() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car;
        parkingBoy.setParkingLot(parkingLot);


        //When
        car = parkingBoy.fetch(null);

        //Then
        assertNull(car);
        assertEquals(parkingBoy.getLastErrorMessage(), "Please provide your parking ticket");
    }

    @Test
    @DisplayName("STORY 2 AC3: should return Not enough position message when parkingLot capacity is full")
    void should_return_not_enough_position_when_capacity_is_full_in_parking_lot() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket finalParkingTicket = new ParkingTicket();
        parkingBoy.setParkingLot(parkingLot);


        //When
        Car finalCar = new Car();
        for(int i = 0; i <= 10; i++){
            finalParkingTicket = parkingBoy.park(finalCar);
        }

        //Then
        assertNull(finalParkingTicket);
        assertEquals(parkingBoy.getLastErrorMessage(), "Not enough position.");
    }

    @Test
    @DisplayName("STORY 3 AC1: should return parking ticket from second parking lot when first parking lot is full")
    void should_return_parking_ticket_from_second_parking_lot_when_first_parking_lot_is_full() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket finalParkingTicket = new ParkingTicket();
        parkingBoy.setParkingLot(firstParkingLot);
        parkingBoy.setParkingLot(secondParkingLot);

        //When
        Car finalCar = new Car();

        for(int i = 0; i <= 10; i++){
            finalParkingTicket = parkingBoy.park(finalCar);
        }

        //Then
        assertNotNull(finalParkingTicket);
    }

    @Test
    @DisplayName("STORY 4 AC1: should be able to park into more spacious parkingLot")
    void should_be_able_to_park_into_more_spacious_parkingLot_by_smart_parking_boy() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingTicket firstParkingTicket;
        ParkingTicket secondParkingTicket;

        smartParkingBoy.setParkingLot(firstParkingLot);
        smartParkingBoy.setParkingLot(secondParkingLot);

        //When
        Car firstCar = new Car();
        Car secondCar = new Car();

        firstParkingTicket = smartParkingBoy.park(firstCar);
        secondParkingTicket = smartParkingBoy.park(secondCar);
        List<ParkingLot> parkingLotList = smartParkingBoy.getParkingLotList();

        //Then
        assertNotNull(firstParkingTicket);
        assertNotNull(secondParkingTicket);
        assertEquals(parkingLotList.get(0).getAvailableParkingPosition(), -9);
        assertEquals(parkingLotList.get(1).getAvailableParkingPosition(), -9);

    }

    @Test
    @DisplayName("STORY 5 AC1: should be able to park into more spacious parkingLot")
    void should_be_able_to_park_where_parking_lot_has_more_position_rate() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingTicket firstParkingTicket;
        ParkingTicket secondParkingTicket;
        ParkingTicket thirdParkingTicket;

        superSmartParkingBoy.setParkingLot(firstParkingLot);
        superSmartParkingBoy.setParkingLot(secondParkingLot);
        parkingBoy.setParkingLot(firstParkingLot);
        parkingBoy.setParkingLot(secondParkingLot);

        //When
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();

        firstParkingTicket = parkingBoy.park(firstCar);
        secondParkingTicket = parkingBoy.park(secondCar);
        thirdParkingTicket = superSmartParkingBoy.park(thirdCar);
        List<ParkingLot> parkingLotList = superSmartParkingBoy.getParkingLotList();

        //Then
        assertNotNull(firstParkingTicket);
        assertNotNull(secondParkingTicket);
        assertNotNull(thirdParkingTicket);
        assertEquals(parkingLotList.get(0).getAvailableParkingPosition(), -8);
        assertEquals(parkingLotList.get(1).getAvailableParkingPosition(), -9);

    }

}

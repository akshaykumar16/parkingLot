package com.parkingLot.parkingLot;

import com.parkingLot.model.Vehicle;

import java.util.List;

public interface IParkingManager {

    void addParkingFloor();
    void parkTheCar(Vehicle vehicle);
    void unParkTheCar(String carNumber, String color);
    boolean isFull();
    List<List<ParkingSpot>> getStatus();
}

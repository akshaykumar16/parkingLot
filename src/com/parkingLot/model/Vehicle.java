package com.parkingLot.model;

import com.parkingLot.enums.VehicleType;
import com.parkingLot.ticketing.ParkingTicket;

public class Vehicle {

    private VehicleType vehicleType;

    private String carNumber;

    private String carColor;

    private boolean isHandicapped;

    private ParkingTicket parkingTicket;


    public Vehicle(VehicleType vehicleType, String carNumber, String carColor, boolean isHandicapped, ParkingTicket parkingTicket) {
        this.vehicleType = vehicleType;
        this.carNumber = carNumber;
        this.carColor = carColor;
        this.isHandicapped = isHandicapped;
        this.parkingTicket = parkingTicket;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public boolean isHandicapped() {
        return isHandicapped;
    }

    public void setHandicapped(boolean handicapped) {
        isHandicapped = handicapped;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType=" + vehicleType +
                ", carNumber='" + carNumber + '\'' +
                ", carColor='" + carColor + '\'' +
                ", isHandicapped=" + isHandicapped +
                '}';
    }
}



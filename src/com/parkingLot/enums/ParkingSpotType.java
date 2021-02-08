package com.parkingLot.enums;

public enum ParkingSpotType {

     HANDICAPPED("Handicapped"), COMPACT("Compact"), LARGE("Large"),
     MOTORBIKE("MotorBike"), ELECTRIC("Electric");

    private String name;

    ParkingSpotType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

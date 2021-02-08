package com.parkingLot.enums;

public enum VehicleType {

    CAR("Car" , 1), TRUCK("Truck", 2), ELECTRIC("Electric", 3), MOTORBIKE("Motorbike", 4);

    private String name;
    private int id;

    VehicleType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId()
    {
        return id;
    }
}

package com.parkingLot.model;

public class VehicleLocation {

  private int floor;
  private int slotNumber;

    public VehicleLocation(int floor, int slotNumber) {
        this.floor = floor;
        this.slotNumber = slotNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    @Override
    public String toString() {
        return
                "floor =" + floor +
                ", slotNumber= " + slotNumber;
    }
}

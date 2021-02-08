package com.parkingLot.parkingLot;

import com.parkingLot.enums.ParkingSpotType;
import com.parkingLot.model.Vehicle;

public class ParkingSpot {

    private int slotNumber;
    private boolean isSlotFree;
    private Vehicle vehicle;
    private final ParkingSpotType parkingSpotType;


    public ParkingSpot(int slotNumber,ParkingSpotType parkingSpotType)
    {
        this.parkingSpotType = parkingSpotType;
        this.slotNumber = slotNumber;
        this.isSlotFree = true;
        this.vehicle = null;
    }

    public boolean isSlotFree()
    {
        return this.isSlotFree;
    }

    public boolean assignVehicle(Vehicle  vehicle)
    {
        if(!isSlotFree) return false;

        this.vehicle = vehicle;
        this.isSlotFree = false;

        return true;
    }

    public boolean removeVehicle()
    {
        if(isSlotFree) return false;
        this.vehicle = null;
        this.isSlotFree = true;
        return true;
    }


    public Vehicle getVehicle()
    {
        if(vehicle == null) return null;
        return this.vehicle;
    }

    public int getSlotNumber()
    {
        return slotNumber;
    }

}

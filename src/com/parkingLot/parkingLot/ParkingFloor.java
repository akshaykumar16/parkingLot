package com.parkingLot.parkingLot;

import com.parkingLot.enums.ParkingSpotType;
import com.parkingLot.model.Vehicle;
import com.parkingLot.model.VehicleParkingSpotMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Parking Floor responsibilites are as follows
 * 1. Park the car if the space is available
 * 2. Un park the car if the car is already parked
 *
 * Made call as package private as one can't initalize the class
 * */
 class ParkingFloor {


    //  Map<String, Integer> trackParkingCars; // Check whether the car has been already parked if so give the slot number


    private int availableSlots; // To Track Number of slots available
    private int currentFloor; // To Indicate the current Floor
    private int totalSlots; // The total number of slots in a this current Floor
    private List<ParkingSpot> parkingSpots; // List to maintain the parking spots and check whether the spot is assigned or not
    // IF Assigned then we get the current vehicle parked
    private Map<ParkingSpotType, List<Integer>> trackParkingSpotTypes; // Map is used to track the number of slots allocated and spot locations according to the parking spot type.

    public ParkingFloor(int currentFloor, int totalSlots) {

        this.trackParkingSpotTypes = new HashMap<>();
        this.parkingSpots = new ArrayList<>(totalSlots);
        allocateParkingSpots(parkingSpots, totalSlots);
        this.availableSlots = totalSlots;
        this.totalSlots = totalSlots;
        this.currentFloor = currentFloor;


    }

    // Returns the allocated slot of the vehicle;
    public int parkCar(Vehicle vehicle) {

        if (isFull()) {
            System.out.println("The current floor  " + currentFloor + " is full We are checking upper level");
            return -1;
        }

        int parkStatus = parkTheCar(vehicle);
        return parkStatus;
    }

    private int parkTheCar(Vehicle vehicle) {

        ParkingSpotType appropriateVehicleType = vehicle.isHandicapped() ? ParkingSpotType.HANDICAPPED :
                VehicleParkingSpotMap.getParkingSpotType(vehicle.getVehicleType());

        int currentAvailableSlot = findAvailableSlot(trackParkingSpotTypes.get(appropriateVehicleType));

        if (currentAvailableSlot == -1) {
            System.out.println("Sorry parking spot for the " + appropriateVehicleType.getName() + " is not available this floor " + this.currentFloor);
            return -2;
        } else {
            parkingSpots.get(currentAvailableSlot).assignVehicle(vehicle);
            System.out.println("The vehicle parked at the floor "+ currentFloor + " and the slot is  "+ currentAvailableSlot);
            availableSlots--;
        }

        return currentAvailableSlot;

    }


    private int findAvailableSlot(List<Integer> spots) {

        for (int spot : spots) {

            if (parkingSpots.get(spot).isSlotFree()) return spot;
        }

        return -1;

    }


    // Calling class will give you the occupied slot of parked Car
    public Vehicle unParkCar(int occupiedSlot) {
        availableSlots++;
        Vehicle vehicle = parkingSpots.get(occupiedSlot).getVehicle();
        parkingSpots.get(occupiedSlot).removeVehicle();
        System.out.println("We successfully un parked your car " + vehicle.getCarNumber() + "at the parking spot " + (occupiedSlot + 1));
        return vehicle;
    }


    public boolean isFull() {
        return availableSlots == 0;
    }


    // This method allocates the parking spot type to the list and also method has a logic to insert into the tracking spot types
    private void allocateParkingSpots(List<ParkingSpot> list, int totalSlots) {

        int equalSlots = totalSlots / 5;

        int index = 1;

        for (ParkingSpotType parkingSpot : ParkingSpotType.values()) {
            trackParkingSpotTypes.put(parkingSpot, new ArrayList<>());
            for (int i = 0; i < equalSlots; i++) {
                list.add(new ParkingSpot(index, parkingSpot));
                trackParkingSpotTypes.get(parkingSpot).add(index);
                index++;
            }

        }

    }

    public void addParkingSpot(ParkingSpotType parkingSpotType) {

        int size = parkingSpots.size();
        parkingSpots.add(new ParkingSpot(size, parkingSpotType));
        trackParkingSpotTypes.get(parkingSpotType).add(size);


    }

    public List<ParkingSpot> getAllParkedVehicles()
    {
        List<ParkingSpot> allocatedParkingSpots = new ArrayList<>();

        for(ParkingSpot  parkingSpot : parkingSpots)
        {
            if(!parkingSpot.isSlotFree())
            {
                allocatedParkingSpots.add(parkingSpot);
            }

        }

        return allocatedParkingSpots;

    }

}

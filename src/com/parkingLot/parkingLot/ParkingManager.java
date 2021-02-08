package com.parkingLot.parkingLot;

import com.parkingLot.model.Vehicle;
import com.parkingLot.model.VehicleLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager implements IParkingManager {

     Map<Integer, ParkingFloor> parkingFloors;
     int numberOfFloors;
     Map<String, VehicleLocation> trackParkingCarFloor;

    public ParkingManager()
    {
        this.parkingFloors = new HashMap<>();
        this.numberOfFloors = 0;
        this.trackParkingCarFloor = new HashMap<>(); //
    }

    @Override
    public void addParkingFloor() {
        parkingFloors.put(numberOfFloors, new ParkingFloor(numberOfFloors, 10));
        numberOfFloors++;
    }

    @Override
    public void parkTheCar(Vehicle vehicle) {

        if(!isFull()){

            String vehicleName = vehicle.getCarNumber() + ":" + vehicle.getCarColor();

            if(trackParkingCarFloor.containsKey(vehicleName))
            {
                System.out.println("Vehicle is already parked at " + trackParkingCarFloor.get(vehicleName));
                return;
            }

            for(int i = 0; i < numberOfFloors; i++)
            {
                // -1 indicates the current Floor is full therefore check the next floor;
                // -2 indicated the current Floor for that vehicle Type is full therefore check the next floor;
                //  0 indicates we are parking the car at the current Floor i.e  i;
                  int parkedLocation = parkingFloors.get(i).parkCar(vehicle);
                  if(parkedLocation >= 0)
                {
                    trackParkingCarFloor.put(vehicle.getCarNumber() + ":"+vehicle.getCarColor(), new VehicleLocation(i, parkedLocation));
                    return;
                }

            }

            System.out.println("Even though we have parking spaces but parking Space for the vehicle type "+vehicle.getVehicleType() + "not available");
        }

        else
        {
            System.out.println("The parking Space is full.......");
        }

    }

    @Override
    public void unParkTheCar(String carNumber, String carColor) {

        if(numberOfFloors == 0) System.out.println("Parking Lot is Empty");

        String vehicleName = carNumber  + ":" +  carColor;

        if(!trackParkingCarFloor.containsKey(vehicleName))
        {
            System.out.println("Vehicle hasn't been parked in this parking lot");
            System.out.println("Please check the car number and try again");
            return;
        }

        VehicleLocation vehicleLocation = trackParkingCarFloor.get(vehicleName);

        int floor = vehicleLocation.getFloor();
        int allocatedSlot = vehicleLocation.getSlotNumber();

        Vehicle vehicle = parkingFloors.get(floor).unParkCar(allocatedSlot);
        System.out.println("The car is parked on the date and time "+vehicle.getParkingTicket().getIssuedAt());
        System.out.println("The amount to pay is  "+ vehicle.getParkingTicket().calculateTheCharge());
        trackParkingCarFloor.remove(vehicleName);

    }



    @Override
    public boolean isFull() {
        for(int i = 0; i < numberOfFloors; i++)
        {
            if(!parkingFloors.get(i).isFull()) return false;
        }

        return true;
    }

    @Override
    public List<List<ParkingSpot>> getStatus() {

        List<List<ParkingSpot>> list = new ArrayList<>();

        for(int i = 0; i < numberOfFloors; i++)
        {
            list.add(parkingFloors.get(i).getAllParkedVehicles());
        }

        return list;

    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}

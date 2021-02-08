package com.parkingLot.parkingLot.parkingStats;

import com.parkingLot.model.Vehicle;
import com.parkingLot.model.VehicleLocation;
import com.parkingLot.parkingLot.ParkingManager;
import com.parkingLot.parkingLot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatusImp implements IParkingStats {

    private ParkingManager parkingManager;
    private List<List<ParkingSpot>> allVehicles;

    public ParkingStatusImp(ParkingManager parkingManager)
    {
        this.parkingManager = parkingManager;
    }

    @Override
    public List<String> getRegistrationNumbersByColor(String color) {

        getStatus();

        List<String> registeredNumbers = new ArrayList<>();

        for(List<ParkingSpot> vehiclesByFloor : allVehicles)
        {
              for(ParkingSpot parkingSpot : vehiclesByFloor)
              {
                 if(parkingSpot.getVehicle().getCarColor().equalsIgnoreCase(color))
                 {
                     registeredNumbers.add(parkingSpot.getVehicle().getCarNumber());
                 }
              }
        }

        return registeredNumbers;

    }

    @Override
    public VehicleLocation getSlotNumberByRegistrationNumberAndColor(String number, String color) {

        getStatus();

        int currentFloor = 0;

       for(List<ParkingSpot> vehiclesByFloor : allVehicles)
       {
           for(ParkingSpot parkingSpot : vehiclesByFloor)
           {
               if(parkingSpot.getVehicle().getCarNumber().equalsIgnoreCase(number) &&
                       parkingSpot.getVehicle().getCarColor().equalsIgnoreCase(color))
               {
                   return new VehicleLocation(currentFloor, parkingSpot.getSlotNumber());
               }
           }
           currentFloor++;
       }

       return null;

    }

    @Override
    public List<VehicleLocation> getAllSlotsByColor(String color) {

       List<VehicleLocation> allVehicleLocationsByColor = new ArrayList<>();
       int currentFloor = 0;
       for(List<ParkingSpot> vehiclesByFloor : allVehicles)
       {
           for (ParkingSpot parkingSpot : vehiclesByFloor)
           {
               if(parkingSpot.getVehicle().getCarColor().equalsIgnoreCase(color))
               {
                   allVehicleLocationsByColor.add(new VehicleLocation(currentFloor, parkingSpot.getSlotNumber()));
               }
           }
       }

       return allVehicleLocationsByColor;

    }


    private void getStatus()
    {
        this.allVehicles = parkingManager.getStatus();
    }
}

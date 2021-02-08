package com.parkingLot.parkingLot.parkingStats;

import com.parkingLot.model.VehicleLocation;

import java.util.List;

public interface IParkingStats {

    List<String> getRegistrationNumbersByColor(String color);
    VehicleLocation getSlotNumberByRegistrationNumberAndColor(String number, String color);
    List<VehicleLocation> getAllSlotsByColor(String color);
}

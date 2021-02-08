package com.parkingLot.model;

import com.parkingLot.enums.ParkingSpotType;
import com.parkingLot.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class VehicleParkingSpotMap {

    private static final Map<VehicleType, ParkingSpotType> mapping;

    static
    {
        mapping = new HashMap<>();
        mapping.put(VehicleType.CAR, ParkingSpotType.COMPACT);
        mapping.put(VehicleType.TRUCK, ParkingSpotType.LARGE);
        mapping.put(VehicleType.MOTORBIKE, ParkingSpotType.MOTORBIKE);
        mapping.put(VehicleType.ELECTRIC, ParkingSpotType.ELECTRIC);
    }

    public static ParkingSpotType getParkingSpotType(VehicleType vehicleType)
    {
        if(!mapping.containsKey(vehicleType)) return null;
        return mapping.get(vehicleType);
    }
}

package com.parkingLot.app;

import com.parkingLot.enums.ParkingSpotType;
import com.parkingLot.enums.VehicleType;
import com.parkingLot.model.Vehicle;
import com.parkingLot.model.VehicleLocation;
import com.parkingLot.parkingLot.ParkingManager;
import com.parkingLot.parkingLot.ParkingSpot;
import com.parkingLot.parkingLot.parkingStats.ParkingStatusImp;
import com.parkingLot.ticketing.ParkingTicket;

import java.io.BufferedReader;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        System.out.println(new Date());

        System.out.println("===================================================================");
        System.out.println("===================      AKSHAY PARKING LOT     ====================");
        System.out.println("===================================================================");

        ParkingManager parkingManager = new ParkingManager();
        ParkingStatusImp parkingStatusImp = new ParkingStatusImp(parkingManager);


        boolean check = true;

        while(check)
        {

            showOptions();
            Scanner scanner = new Scanner(System.in);
            int next = 0;
            boolean isRetry = true;

            while(isRetry)
            {
                System.out.println("Please Enter one of the number from the displayed options");
                if(scanner.hasNextInt())
                {
                    next = scanner.nextInt();
                    isRetry = false;
                }
                else
                {
                    scanner.next();
                    System.out.println("oops you have entered invalid input. Please re try again");
                }

            }


            switch (next)
            {
                case 1 :
                    parkingManager.addParkingFloor();
                    System.out.println("Created new parking Floor and the number of Floors  "+ parkingManager.getNumberOfFloors());
                    break;


                case 2:

                    Vehicle vehicle = getVehicleDetails(scanner);
                    parkingManager.parkTheCar(vehicle);
                    break;

                case 3:
                    System.out.println("Enter the car number");
                    String carNumber = scanner.next();
                    System.out.println("Enter the car color");
                    String color = scanner.next();
                    parkingManager.unParkTheCar(carNumber, color);
                    break;

                case 4:
                    List<List<ParkingSpot>> allParkingSpots =  parkingManager.getStatus();
                    if(allParkingSpots.isEmpty()) System.out.println("No cars parked in the parking lot......");
                    else
                    {
                        int currentFloor = 0;

                        for(List<ParkingSpot> parkingSpots : allParkingSpots)
                        {
                            System.out.println("The vehicles parked at the floor  " + currentFloor);

                            System.out.println("Slot Number "+"\t\t\t\t\t" + "Car Number " + "\t\t\t\t\t" + "Car color");

                            for(ParkingSpot parkingSpot : parkingSpots)
                            {
                                System.out.print(parkingSpot.getSlotNumber() + "\t\t\t\t\t"+parkingSpot.getVehicle().getCarNumber() + "\t\t\t\t\t"+parkingSpot.getVehicle().getCarColor());
                                System.out.println();
                            }
                            currentFloor++;
                        }

                    }
                    break;

                case 5:
                    System.out.println("Please type the color to display the list of registered car numbers ");
                    String carColor = scanner.next();
                   List<String> registratedNumbers = parkingStatusImp.getRegistrationNumbersByColor(carColor);

                   for(String number : registratedNumbers)
                   {
                       System.out.println(number);
                   }
                    break;
                case 6:
                    System.out.println("Please type the registered number to display on the allocated slot number ");
                    String registeredNumber = scanner.next();
                    String carColor1 = scanner.next();
                   VehicleLocation vehicleLocation = parkingStatusImp.getSlotNumberByRegistrationNumberAndColor(registeredNumber, carColor1);
                    System.out.println(vehicleLocation);
                    break;

                case 7:
                    System.out.println("Please type the color to display all the vehicle locations of car");
                    String carColor2 = scanner.next();
                   List<VehicleLocation> vehicleLocations = parkingStatusImp.getAllSlotsByColor(carColor2);
                    System.out.println(vehicleLocations);
                    break;
                case 8:
                    System.out.println("Exiting Bye ............");
                    check = false;
                    break;

                 default:
                       System.out.println("Please enter the number with in the range");
                       break;

            }


        }


    }

    private static void showOptions()
    {
        System.out.println("==========================================================================================");
        System.out.println("1. Add the parking Floor");
        System.out.println("2. Park the Car");
        System.out.println("3. Unpark the Car");
        System.out.println("4. Status of the parking lot");
        System.out.println("5. Get all the registered car numbers by car color");
        System.out.println("6. Get the slot number by registered car number and car color");
        System.out.println("7. Get all Vehicle Locations by car color");
        System.out.println("8. Exit the Parking lot");
    }

    private static void showTypes()
    {
        System.out.println("1.Car");
        System.out.println("2.Truck");
        System.out.println("3.MotorBike");
        System.out.println("4.Electric");
    }

    private static VehicleType getVehicleType(int id)
    {
        for(VehicleType vehicleType : VehicleType.values())
        {
             if( vehicleType.getId() == id) return vehicleType;
        }

        return null;
    }

    private static Vehicle getVehicleDetails(Scanner scanner)
    {
        System.out.println("Please select the car type");
        showTypes();
        int carType = scanner.nextInt();
        VehicleType currentVehiceType = getVehicleType(carType);
        System.out.println("Enter the car number");
        String carNumber = scanner.next();
        System.out.println("Enter the car color");
        String color = scanner.next();
        System.out.println("Does your car holds handicap disability");
        System.out.println("Please Type either true or false");
        boolean isHandicapped = scanner.nextBoolean();
        Vehicle newVehicle = new Vehicle(currentVehiceType, carNumber , color, isHandicapped, new ParkingTicket());
        return newVehicle;
    }
}

/*
*  While Un Parking the are you checking the car Type
*
* Re Group the packages
*
*
*  */

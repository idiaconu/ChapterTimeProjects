package com.ing.digit.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a Parking Lot application
 * 
 * Description:
 * 
 * Can support multiple parking spot type: MOTO, CAR, VAN, TRUCK etc. A car can park on a CAR, VAN or TRUCK spot.
 * Can support multiple entrances.
 * When a car wants to enter it provides vehicle type and license plate. If a spot is available then give the driver the spot id.
 * When a car wants to leave it provides the license plate.
 * 
 * Mob programming structure:
 * 
 * Mob 
 *      The mob refers to the group of developers working together. 
 *      They are responsible for discussing multiple alternative implementations and selecting the best course of action.
 *      
 * Navigator (10 min rotation) 
 *      This developer is responsible for listening to the mob's discussion and communicating instructions to the driver.
 *      
 * Driver (10 min rotation).
 *      This developer is responsible for converting the navigator's instructions into code
 * 
 * 
 * Schedule: 
 *     Irina/Radu
 *     Daniel/Bogdan
 *     Cosmin/David
 *     Edy/Oana
 * */

public class ParkingLot {
    
    private List<ParkingSpace> parkingSpaces = new ArrayList<>(); 
    
    public ParkingLot(Map<VehicleType, Integer> availableSpaces) {
       for (var availableVechicleTypeSpace : availableSpaces.entrySet()) {
           
           for (int i = 0; i < availableVechicleTypeSpace.getValue(); i++) {
               String id = availableVechicleTypeSpace.getKey().toString() + i;
               ParkingSpace p = new ParkingSpace(availableVechicleTypeSpace.getKey(), id);
               parkingSpaces.add(p);
           }
       }
        
    }
    
    public synchronized Optional<String> enterParkingLot(String licensePlate, VehicleType vehType) {
        
        ParkingSpace parkingSpace = parkingSpaces.stream()
                .filter(p -> p.type.equals(vehType) && p.getLicensePlate() == null)
                .findFirst().orElse(null);
        
        if (parkingSpace != null) {
            parkingSpace.setLicensePlate(licensePlate);
            return Optional.of(parkingSpace.id);
        }
        
        return Optional.empty();
        
    }

    public synchronized void exitParkingLot(String licensePlate) {
        
        ParkingSpace parkingSpace = parkingSpaces.stream()
                .filter(p -> p.getLicensePlate().equals(licensePlate))
                .findFirst().orElse(null);
        
        if (parkingSpace != null) {
            parkingSpace.setLicensePlate(null);
        }
    }
    
    
    public enum VehicleType {
        MOTO,
        CAR,
        VAN,
        TRUCK
    }
}

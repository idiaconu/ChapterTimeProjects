package com.ing.digit.parkinglot;

import com.ing.digit.parkinglot.ParkingLot.VehicleType;

public class ParkingSpace {

    public final VehicleType type;
    public final String id;
    private volatile String licensePlate;
    
    public ParkingSpace(VehicleType type, String id) {
        this.type = type;
        this.id = id;
    }
    
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
}

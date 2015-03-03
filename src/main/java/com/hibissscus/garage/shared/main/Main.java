package com.hibissscus.garage.shared.main;

import com.hibissscus.garage.shared.model.vehicle.Car;
import com.hibissscus.garage.shared.model.vehicle.Motorbike;
import com.hibissscus.garage.shared.model.vehicle.Truck;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;


public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ParkingGarage garage = new ParkingGarage.Builder(1, 1, 3).build();

        Vehicle car = new Car();
        Vehicle motorbike = new Motorbike();
        Vehicle truck = new Truck();

        garage.parkVehicle(truck);
        garage.print();
        garage.unParkVehicle(truck);
        garage.parkVehicle(car);
        garage.print();
        garage.parkVehicle(motorbike);
        garage.print();

        garage.parkVehicle(truck);
        garage.print();
    }
}
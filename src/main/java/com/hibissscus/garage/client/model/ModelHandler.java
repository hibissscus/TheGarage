package com.hibissscus.garage.client.model;

import com.google.gwt.user.client.Random;
import com.hibissscus.garage.shared.main.ParkingGarage;
import com.hibissscus.garage.shared.model.vehicle.Car;
import com.hibissscus.garage.shared.model.vehicle.Motorbike;
import com.hibissscus.garage.shared.model.vehicle.Truck;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple model handler
 * manage {@link Vehicle} list
 *
 * @author hibissscus
 */
public class ModelHandler {

    List<Vehicle> vehicles = new ArrayList<Vehicle>();
    ParkingGarage garage;

    public ModelHandler() {

    }

    public ParkingGarage getGarage() {
        return garage;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean park(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.equals(vehicle) && !v.isParked()) {
                return garage.parkVehicle(v);
            }
        }
        return false;
    }

    public boolean unpark(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.equals(vehicle) && v.isParked()) {
                return garage.unParkVehicle(v);
            }
        }
        return false;
    }


    public void newRandomList() {
        vehicles = new ArrayList<Vehicle>();

        int cars = Random.nextInt(16);
        int bikes = Random.nextInt(7);
        int trucks = Random.nextInt(3);
        for (int i = 0; i < cars; i++) {
            vehicles.add(new Car());
        }
        for (int i = 0; i < bikes; i++) {
            vehicles.add(new Motorbike());
        }
        for (int i = 0; i < trucks; i++) {
            vehicles.add(new Truck());
        }
    }

    public void newTheGarage(int level, int row, int column) {
        garage = new ParkingGarage.Builder(level, row, column).build();
    }
}

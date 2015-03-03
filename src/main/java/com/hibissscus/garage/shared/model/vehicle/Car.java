package com.hibissscus.garage.shared.model.vehicle;

import com.hibissscus.garage.shared.main.VehicleType;
import com.hibissscus.garage.shared.util.RandomIcon;

/**
 * The vehicle type Car.
 *
 * @author hibissscus
 */
public class Car extends Vehicle {

    /**
     * Instantiates a new Car.
     */
    public Car() {
        vehicleType = VehicleType.CAR;
    }

    @Override
    public String getIcon() {
        if (iconUrl == null) iconUrl = RandomIcon.getRandomUrlIcon(getVehicleType());
        return iconUrl;
    }
}
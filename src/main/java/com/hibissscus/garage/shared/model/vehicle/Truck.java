package com.hibissscus.garage.shared.model.vehicle;

import com.hibissscus.garage.shared.main.VehicleType;
import com.hibissscus.garage.shared.util.RandomIcon;

/**
 * The vehicle type Truck.
 *
 * @author hibissscus
 */
public class Truck extends Vehicle {

    /**
     * Instantiates a new Truck.
     */
    public Truck() {
        vehicleType = VehicleType.TRUCK;
    }

    @Override
    public String getIcon() {
        if(iconUrl== null) iconUrl = RandomIcon.getRandomUrlIcon(getVehicleType());
        return iconUrl;
    }

}
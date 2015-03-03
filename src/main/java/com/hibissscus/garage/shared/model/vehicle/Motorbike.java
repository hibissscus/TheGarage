package com.hibissscus.garage.shared.model.vehicle;

import com.hibissscus.garage.shared.main.VehicleType;
import com.hibissscus.garage.shared.util.RandomIcon;

/**
 * The vehicle type Motorbike.
 *
 * @author hibissscus
 */
public class Motorbike extends Vehicle {

    /**
     * Instantiates a new Motorbike.
     */
    public Motorbike() {
        vehicleType = VehicleType.MOTORBIKE;
    }

    @Override
    public String getIcon() {
        if(iconUrl== null) iconUrl = RandomIcon.getRandomUrlIcon(getVehicleType());
        return iconUrl;
    }
}
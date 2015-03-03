package com.hibissscus.garage.shared.model.vehicle;

import com.hibissscus.garage.shared.main.ParkingSpace;
import com.hibissscus.garage.shared.main.VehicleType;
import com.hibissscus.garage.shared.util.UUID;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Vehicle.
 *
 * @author hibissscus
 */
public abstract class Vehicle implements Serializable {

    /**
     * The License plate.
     */
    protected String licensePlate;
    /**
     * The Vehicle type.
     */
    protected VehicleType vehicleType;
    /**
     * The Spaces.
     */
    protected ArrayList<ParkingSpace> spaces = new ArrayList<ParkingSpace>();
    /**
     * The Icon url.
     */
    protected String iconUrl;

    /**
     * Instantiates a new Vehicle.
     */
    Vehicle() {
        licensePlate = generatePseudoLicensePlate();
    }

    /**
     * Gets license plate.
     *
     * @return the license plate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Gets vehicle type.
     *
     * @return the vehicle type
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Gets spaces.
     *
     * @return the spaces
     */
    public ArrayList<ParkingSpace> getSpaces() {
        return spaces;
    }

    /**
     * Is vehicle parked.
     *
     * @return is vehicle parked
     */
    public boolean isParked() {
        return !spaces.isEmpty();
    }

    /**
     * Pseudo license plate generation.
     *
     * @return pseudo license plate
     */
    public static String generatePseudoLicensePlate() {
        String[] split = UUID.randomUUID().split("-");
        return split[split.length - 1];
    }

    /**
     * Gets vehicle icon.
     *
     * @return the icon
     */
    public abstract String getIcon();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (licensePlate != null ? !licensePlate.equals(vehicle.licensePlate) : vehicle.licensePlate != null)
            return false;
        return vehicleType == vehicle.vehicleType;

    }

    @Override
    public int hashCode() {
        int result = licensePlate != null ? licensePlate.hashCode() : 0;
        result = 31 * result + (vehicleType != null ? vehicleType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
package com.hibissscus.garage.shared.main;

import com.hibissscus.garage.shared.model.vehicle.Vehicle;

/**
 * The Garage for vehicles.
 */
public class ParkingGarage {

    private int levels;
    private ParkingLevel[] parkingLevels;
    private boolean cannotPark;

    private ParkingGarage(int levels, int row, int column) {
        this.levels = levels;
        this.parkingLevels = new ParkingLevel[levels];

        for (int i = 0; i < levels; i++) {
            parkingLevels[i] = new ParkingLevel(i, row, column);
        }
    }

    /**
     * Park vehicle.
     *
     * @param vehicle the vehicle
     * @return the boolean
     */
    public boolean parkVehicle(Vehicle vehicle) {
        boolean parked = false;

        for (int i = 0; i < parkingLevels.length && !parked; i++) {
            parked = parkingLevels[i].parkVehicleOnLevel(vehicle);
        }

        cannotPark = !parked;
        if (cannotPark) {
            System.out.println("Cannot park vehicle: " + vehicle);
        }
        return parked;
    }


    /**
     * Un park vehicle.
     *
     * @param vehicle the vehicle
     * @return the boolean
     */
    public boolean unParkVehicle(Vehicle vehicle) {
        cannotPark = false;
        if (vehicle != null && vehicle.isParked()) {
            ParkingSpace space = vehicle.getSpaces().get(0);
            parkingLevels[space.getLevel()].unParkVehicleOnLevel(vehicle);
            vehicle.getSpaces().clear();
            return true;
        }
        return false;
    }

    /**
     * Free space available.
     *
     * @return the int
     */
    public int freeSpaceAvailable() {
        int freeSpaces = 0;
        for (ParkingLevel parkingLevel : parkingLevels) {
            freeSpaces += parkingLevel.freeSpaceAvailable();
        }
        return freeSpaces;
    }


    /**
     * Get parking levels.
     *
     * @return the parking level [ ]
     */
    public ParkingLevel[] getParkingLevels() {
        return parkingLevels;
    }

    /**
     * Gets levels.
     *
     * @return the levels
     */
    public int getLevels() {
        return levels;
    }

    /**
     * Is cannot park.
     *
     * @return the boolean
     */
    public boolean isCannotPark() {
        return cannotPark;
    }

    /**
     * Print void.
     */
    public void print() {
        for (ParkingLevel parkingLevel : parkingLevels) {
            parkingLevel.print();
        }
    }

    /**
     * Builder.
     */
    public static class Builder {
        private final int levels;
        private final int row;
        private final int column;

        private boolean withElevator = false;

        /**
         * Instantiates a new Builder.
         *
         * @param levels the levels
         * @param row    the row
         * @param column the column
         */
        public Builder(int levels, int row, int column) {
            this.levels = levels;
            this.row = row;
            this.column = column;
        }

        /**
         * With elevator.
         *
         * @param value the value
         * @return the builder
         */
        public Builder withElevator(boolean value) {
            withElevator = value;
            return this;
        }

        /**
         * Build parking garage.
         *
         * @return the parking garage
         */
        public ParkingGarage build() {
            return new ParkingGarage(levels, row, column);
        }
    }
}
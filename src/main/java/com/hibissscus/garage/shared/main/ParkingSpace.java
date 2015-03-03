package com.hibissscus.garage.shared.main;

import com.hibissscus.garage.shared.model.vehicle.Vehicle;

/**
 * Parking space for vehicle.
 */
public class ParkingSpace {

    /**
     * Level of this parking space.
     */
    private int level;
    /**
     * Parking space number of row.
     */
    private int row;
    /**
     * Parking space number of column.
     */
    private int column;
    /**
     * Type of the space in TheGarage.
     */
    private SpaceType type;
    /**
     * Vehicle for this parking space.
     */
    private Vehicle vehicle;

    /**
     * Instantiates a new Parking space.
     *
     * @param level  the level
     * @param row    the row
     * @param column the column
     */
    public ParkingSpace(int level, int row, int column) {
        this.level = level;
        this.row = row;
        this.column = column;
        this.type = SpaceType.FREE;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public SpaceType getType() {
        return type;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets vehicle.
     *
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Park vehicle.
     *
     * @param vehicle the vehicle
     */
    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.type = SpaceType.RESERVED;
    }

    /**
     * Un park vehicle.
     */
    public void unParkVehicle() {
        this.vehicle = null;
        this.type = SpaceType.FREE;
    }

    /**
     * Print space symbol.
     */
    public void print() {
        if (vehicle != null) {
            this.vehicle.getVehicleType().print();
        } else {
            this.type.print();
        }
    }
}
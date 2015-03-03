package com.hibissscus.garage.shared.main;

import com.hibissscus.garage.shared.model.vehicle.Vehicle;

/**
 * Parking level.
 */
public class ParkingLevel {

    private int level;
    private int row;
    private int column;
    private ParkingSpace[][] spaces;

    /**
     * Instantiates a new Parking level.
     *
     * @param level  the level
     * @param row    the row
     * @param column the column
     */
    public ParkingLevel(int level, int row, int column) {
        this.level = level;
        this.row = row;
        this.column = column;
        spaces = new ParkingSpace[row][column];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                spaces[r][c] = new ParkingSpace(level, r, c);
            }
        }

    }

    /**
     * Number of available free space.
     *
     * @return available free space.
     */
    public int freeSpaceAvailable() {
        int freeSpaces = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (spaces[r][c].getType() == SpaceType.FREE) {
                    freeSpaces++;
                }
            }
        }
        return freeSpaces;
    }

    /**
     * Un-park vehicle from level.
     *
     * @param vehicle the vehicle
     */
    public void unParkVehicleOnLevel(Vehicle vehicle) {
        for (ParkingSpace space : vehicle.getSpaces()) {
            spaces[space.getRow()][space.getColumn()].unParkVehicle();
        }
    }

    /**
     * Park vehicle on level.
     *
     * @param vehicle the vehicle
     * @return the boolean
     */
    public boolean parkVehicleOnLevel(Vehicle vehicle) {
        int size = vehicle.getVehicleType().getSize();

        for (int r = 0; r < getRow(); r++) {
            int columnPosition = getParkPositionInRow(r, size);
            if (columnPosition >= 0) {
                parkInRow(r, columnPosition, vehicle);
                return true;
            }
        }
        for (int c = 0; c < getColumn(); c++) {
            int rowPosition = getParkPositionInColumn(c, size);
            if (rowPosition >= 0) {
                parkInColumn(c, rowPosition, vehicle);
                return true;
            }
        }
        return false;
    }

    private int getParkPositionInRow(int row, int size) {
        int numberOfPossiblePosition = -1;
        int freeSpace = 0;
        for (int c = 0; c < getColumn(); c++) {
            if (spaces[row][c].getType() == SpaceType.FREE) {
                freeSpace++;
            } else {
                freeSpace = 0;
            }

            if (freeSpace == size)
                return c - size + 1;
        }

        return numberOfPossiblePosition;
    }

    private int getParkPositionInColumn(int column, int size) {
        int numberOfPossiblePosition = -1;
        int freeSpace = 0;
        for (int r = 0; r < getRow(); r++) {
            if (spaces[r][column].getType() == SpaceType.FREE) {
                freeSpace++;
            } else {
                freeSpace = 0;
            }

            if (freeSpace == size)
                return r - size + 1;
        }

        return numberOfPossiblePosition;
    }

    private void parkInRow(int row, int columnPosition, Vehicle vehicle) {
        int size = vehicle.getVehicleType().getSize();
        if (columnPosition >= 0 && columnPosition < getColumn()) {
            for (int c = columnPosition; c < columnPosition + size; c++) {
                vehicle.getSpaces().add(spaces[row][c]);
                spaces[row][c].parkVehicle(vehicle);
            }
        }
    }

    private void parkInColumn(int column, int rowPosition, Vehicle vehicle) {
        int size = vehicle.getVehicleType().getSize();
        if (rowPosition >= 0 && rowPosition < getRow()) {
            for (int r = rowPosition; r < rowPosition + size; r++) {
                vehicle.getSpaces().add(spaces[r][column]);
                spaces[r][column].parkVehicle(vehicle);
            }
        }
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
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get spaces.
     *
     * @return the parking space [ ] [ ]
     */
    public ParkingSpace[][] getSpaces() {
        return spaces;
    }

    /**
     * Print level.
     */
    public void print() {
        System.out.println("Level: " + level);
        for (int i = 0; i < column + 2; i++) {
            System.out.print("-");
        }
        System.out.println();

        for (int r = 0; r < row; r++) {
            System.out.print("|");
            for (int c = 0; c < column; c++) {
                spaces[r][c].print();
            }
            System.out.println("|");
        }

        for (int i = 0; i < column + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
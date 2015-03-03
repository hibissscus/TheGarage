package com.hibissscus.garage.shared.main;

/**
 * The enum Vehicle type.
 */
public enum VehicleType {

    /**
     * The MOTORBIKE.
     */
    MOTORBIKE("m", 1),
    /**
     * The CAR.
     */
    CAR("c", 2),
    /**
     * The TRUCK.
     */
    TRUCK("t", 3);

    private int size;
    private String symbol;

    /**
     * Instantiates a new Vehicle type.
     *
     * @param symbol the symbol
     * @param size   the size
     */
    VehicleType(String symbol, int size) {
        this.symbol = symbol;
        this.size = size;
    }

    /**
     * From size.
     *
     * @param size the size
     * @return the vehicle type
     */
    public static VehicleType fromSize(int size) {
        for (VehicleType v : VehicleType.values()) {
            if (size == v.getSize()) {
                return v;
            }
        }
        return null;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Print void.
     */
    public void print() {
        System.out.print(symbol);
    }
}
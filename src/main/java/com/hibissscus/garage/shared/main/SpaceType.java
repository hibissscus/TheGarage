package com.hibissscus.garage.shared.main;

/**
 * Type of the space in TheGarage.
 *
 * @author hibissscus
 */
public enum SpaceType {

    /**
     * The FREE.
     */
    FREE("*"),
    /**
     * The RESERVED.
     */
    RESERVED("x"),
    /**
     * The UNAVAILABLE.
     */
    UNAVAILABLE("#");

    private String symbol;

    /**
     * Instantiates a new Space type.
     *
     * @param symbol the symbol
     */
    SpaceType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Print symbol.
     */
    public void print() {
        System.out.print(symbol);
    }
}

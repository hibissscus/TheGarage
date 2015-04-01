package com.hibissscus.garage.server.jasper;

import java.io.Serializable;

/**
 * POJO class to jasper.
 *
 * @author hibissscus
 */
public class JasperVehicle implements Serializable {

    private String iconUrl;
    private String licensePlate;
    private String parkingPosition;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getParkingPosition() {
        return parkingPosition;
    }

    public void setParkingPosition(String parkingPosition) {
        this.parkingPosition = parkingPosition;
    }
}
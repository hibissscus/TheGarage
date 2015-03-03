package com.hibissscus.garage.shared.util;


import com.google.gwt.user.client.Random;
import com.hibissscus.garage.shared.main.VehicleType;

/**
 * The type Random icon.
 *
 * @author hibissscus
 */
public class RandomIcon {

    /**
     * Gets random url icon.
     *
     * @param vehicleType the vehicle type
     * @return the random url icon
     */
    public static String getRandomUrlIcon(VehicleType vehicleType) {
        if (vehicleType == VehicleType.CAR) return getRandomUrlIconCar();
        else if (vehicleType == VehicleType.MOTORBIKE) return getRandomUrlIconMoto();
        else return getRandomUrlIconTruck();

    }

    /**
     * Gets random url icon car.
     *
     * @return the random url icon car
     */
    public static String getRandomUrlIconCar() {
        switch (Random.nextInt(16)) {
            case 1:
                return "http://megaicons.net/static/img/icons_sizes/319/820/64/vw-beetle-icon.png";
            case 2:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Audi-TT-icon.png";
            case 3:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/BMW-icon.png";
            case 4:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Ford-Mustang-icon.png";
            case 5:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Hummer-icon.png";
            case 6:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Lamborghini-icon.png";
            case 7:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Mini-icon.png";
            case 8:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Volkswagen-Beetle-icon.png";
            case 9:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Volkswagen-Bulli-Bus-icon.png";
            case 10:
                return "http://icons.iconarchive.com/icons/searchallwreckers/car/64/Chrysler-300-icon.png";
            case 11:
                return "http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/bmw-mini-icon.png";
            case 12:
                return "http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/camaro-icon.png";
            case 13:
                return "http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/chevrolet-impala-icon.png";
            case 14:
                return "http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/ferrari-icon.png";
            case 15:
                return "http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/yellow-pickup-icon.png";
        }
        return "http://megaicons.net/static/img/icons_sizes/319/820/64/vw-beetle-icon.png";
    }

    /**
     * Gets random url icon moto.
     *
     * @return the random url icon moto
     */
    public static String getRandomUrlIconMoto() {
        switch (Random.nextInt(4)) {
            case 1:
                return "http://icons.iconarchive.com/icons/iconshow/transport/64/Motorcycle-icon.png";
            case 2:
                return "http://icons.iconarchive.com/icons/fixicon/vehicules/48/motorbike-icon.png";
            case 3:
                return "http://icons.iconarchive.com/icons/fixicon/vehicules/48/motor-icon.png";
        }

        return "http://icons.iconarchive.com/icons/iconshow/transport/64/Motorcycle-icon.png";
    }

    /**
     * Gets random url icon truck.
     *
     * @return the random url icon truck
     */
    public static String getRandomUrlIconTruck() {
        switch (Random.nextInt(4)) {
            case 1:
                return "http://icons.iconarchive.com/icons/bartkowalski/1960-matchbox-cars/64/DAF-Tipper-Container-Truck-icon.png";
            case 2:
                return "http://icons.iconarchive.com/icons/bartkowalski/1960-matchbox-cars/64/Grit-Spreader-icon.png";
            case 3:
                return "http://icons.iconarchive.com/icons/bartkowalski/1960-matchbox-cars/64/Stake-Truck-icon.png";
        }

        return "http://icons.iconarchive.com/icons/bartkowalski/1960-matchbox-cars/64/DAF-Tipper-Container-Truck-icon.png";
    }
}

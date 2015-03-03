package com.hibissscus.garage.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;

/**
 * Park event.
 *
 * @author hibissscus
 */
public class ParkEvent extends GwtEvent<ParkEventHandler> {

    public static Type<ParkEventHandler> TYPE = new Type<ParkEventHandler>();

    Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkEvent(Vehicle vehicle) {
        this.vehicle = vehicle;

    }

    @Override
    protected void dispatch(ParkEventHandler handler) {
        handler.onParkEventHandler(this);
    }

    @Override
    public Type<ParkEventHandler> getAssociatedType() {
        return TYPE;
    }

}

package com.hibissscus.garage.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;

import java.util.List;

/**
 * PDF event.
 *
 * @author hibissscus
 */
public class PdfEvent extends GwtEvent<PdfEventHandler> {

    public static Type<PdfEventHandler> TYPE = new Type<PdfEventHandler>();

    List<Vehicle> vehicles;

    public List<Vehicle> getVehicle() {
        return vehicles;
    }

    public PdfEvent(List<Vehicle> vehicles) {
        this.vehicles = vehicles;

    }

    @Override
    protected void dispatch(PdfEventHandler handler) {
        handler.onPdfEventHandler(this);
    }

    @Override
    public Type<PdfEventHandler> getAssociatedType() {
        return TYPE;
    }

}

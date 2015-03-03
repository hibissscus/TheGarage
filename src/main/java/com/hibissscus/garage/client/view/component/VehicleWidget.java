package com.hibissscus.garage.client.view.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.hibissscus.garage.client.event.ParkEvent;
import com.hibissscus.garage.shared.main.ParkingSpace;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;

/**
 * The type {@link Vehicle} widget.
 */
public class VehicleWidget extends Composite {

    private static VehicleWidgetUiBinder uiBinder = GWT.create(VehicleWidgetUiBinder.class);

    /**
     * The interface {@link Vehicle}  widget ui binder.
     */
    interface VehicleWidgetUiBinder extends UiBinder<Widget, VehicleWidget> {
    }

    /**
     * The Image.
     */
    @UiField
    Image image;

    /**
     * The Text box.
     */
    @UiField
    InlineLabel textBox;

    /**
     * The park/unpark button.
     */
    @UiField
    Button button;

    /**
     * The parking box.
     */
    @UiField
    InlineLabel parking;

    /**
     * Current {@link Vehicle}
     */
    private Vehicle vehicle;

    /**
     * Event bus
     */
    private SimpleEventBus eventBus;

    /**
     * Instantiates a new {@link Vehicle}  widget.
     */
    public VehicleWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * Instantiates a new {@link Vehicle} widget.
     *
     * @param vehicle  the vehicle
     * @param eventBus the event bus
     */
    public VehicleWidget(Vehicle vehicle, SimpleEventBus eventBus) {
        this.eventBus = eventBus;
        // init display
        initWidget(uiBinder.createAndBindUi(this));
        this.vehicle = vehicle;
        // format display
        this.image.setUrl(vehicle.getIcon());
        this.textBox.setText(vehicle.getLicensePlate());

        if (vehicle != null && vehicle.isParked()) {
            this.button.setText("unpark");
            this.parking.setText(getParkingPosition(vehicle));
        } else {
            this.button.setText("park");
        }
    }

    /**
     * Get string representation of parking position.
     *
     * @param vehicle vehicle
     * @return parking position
     */
    private String getParkingPosition(Vehicle vehicle) {
        StringBuilder sb = new StringBuilder("parking position: ");
        sb.append("level[");
        sb.append(vehicle.getSpaces().get(0).getLevel());
        sb.append("] ");
        for (ParkingSpace space : vehicle.getSpaces()) {
            sb.append("[");
            sb.append(space.getRow());
            sb.append(",");
            sb.append(space.getColumn());
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * On park button click.
     *
     * @param e the e
     */
    @UiHandler("button")
    void onParkButtonClick(ClickEvent e) {
        eventBus.fireEvent(new ParkEvent(vehicle));
    }

}

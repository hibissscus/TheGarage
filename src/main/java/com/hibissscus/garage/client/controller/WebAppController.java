package com.hibissscus.garage.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.inject.Inject;
import com.hibissscus.garage.client.event.*;
import com.hibissscus.garage.client.model.ModelHandler;
import com.hibissscus.garage.client.view.MainPanel;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;

import java.util.List;

/**
 * Web App Controller manage all business events and communicate with server services.
 *
 * @author hibissscus
 */
public class WebAppController {

    /**
     * Event Bus
     */
    private SimpleEventBus eventBus;

    /**
     * Model Handler
     */
    private ModelHandler modelHandler;

    /**
     * Main panel UI
     */
    private MainPanel mainPanel;

    @Inject
    public WebAppController(SimpleEventBus eventBus, ModelHandler modelHandler, MainPanel mainPanel) {
        this.eventBus = eventBus;
        this.modelHandler = modelHandler;
        this.mainPanel = mainPanel;
    }

    /**
     * Bind all events handler
     */
    public void bindHandlers() {

        eventBus.addHandler(IpAddressEvent.TYPE, new IpAddressEventHandler() {
            @Override
            public void onIpAddressEventHandler(IpAddressEvent event) {
                WebAppController.this.getIpAddress();
            }
        });
        eventBus.addHandler(ParkEvent.TYPE, new ParkEventHandler() {
            @Override
            public void onParkEventHandler(ParkEvent event) {
                WebAppController.this.park(event.getVehicle());
            }
        });
        eventBus.addHandler(PdfEvent.TYPE, new PdfEventHandler() {
            @Override
            public void onPdfEventHandler(PdfEvent event) {
                WebAppController.this.jasperReport(event.getVehicle());
            }
        });

        // start: store client ip
        eventBus.fireEvent(new IpAddressEvent());
    }

    /**
     * Park {@link Vehicle}
     */
    void park(Vehicle vehicle) {
        if (!vehicle.isParked()) {
            modelHandler.park(vehicle);
        } else {
            modelHandler.unpark(vehicle);
        }

        mainPanel.reloadTable();
        mainPanel.showTheGarage();
    }


    /**
     * jasper
     */
    void jasperReport(List<Vehicle> vehicles) {
        String pageBaseUrl = GWT.getHostPageBaseURL();

        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/download");
        requestBuilder.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                // some error handling code here

            }

            public void onResponseReceived(Request request, Response response) {
            }
        });

        try {
            requestBuilder.send();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ip address
     */
    void getIpAddress() {
        String pageBaseUrl = GWT.getHostPageBaseURL();

        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/rest/ip");
        requestBuilder.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                // some error handling code here

            }

            public void onResponseReceived(Request request, Response response) {
                if (Response.SC_OK == response.getStatusCode()) {
                    String ip = response.getText();
                }
            }
        });

        try {
            requestBuilder.send();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
}

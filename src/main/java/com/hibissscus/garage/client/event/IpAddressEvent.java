package com.hibissscus.garage.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * IP address event
 *
 * @author hibissscus
 */
public class IpAddressEvent extends GwtEvent<IpAddressEventHandler> {

    public static Type<IpAddressEventHandler> TYPE = new Type<IpAddressEventHandler>();

    public IpAddressEvent() {

    }

    @Override
    public Type<IpAddressEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(IpAddressEventHandler handler) {
        handler.onIpAddressEventHandler(this);
    }

}

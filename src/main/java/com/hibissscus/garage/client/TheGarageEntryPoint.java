package com.hibissscus.garage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.hibissscus.garage.client.controller.WebAppController;
import com.hibissscus.garage.client.gin.GwtWebAppGinjector;
import com.hibissscus.garage.client.view.MainPanel;
import com.hibissscus.garage.client.view.resource.ApplicationResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * <p/>
 * Entry point for GWT module.
 *
 * @author hibissscus
 */
public class TheGarageEntryPoint implements EntryPoint {

    /**
     * Gin injector
     */
    private final GwtWebAppGinjector injector = GWT.create(GwtWebAppGinjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // ensure resources are injected
        ApplicationResources.RESOURCES.style().ensureInjected();
        // get controler from gin jector
        WebAppController controller = injector.getWebAppController();
        // bind event handlers
        controller.bindHandlers();
        // get main panel
        MainPanel mainPanel = injector.getMainPanel();
        // add for display
        RootLayoutPanel.get().add(mainPanel);
    }
}

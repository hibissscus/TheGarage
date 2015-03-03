package com.hibissscus.garage.client.gin;

import com.hibissscus.garage.client.controller.WebAppController;
import com.hibissscus.garage.client.model.ModelHandler;
import com.hibissscus.garage.client.view.MainPanel;
import com.hibissscus.garage.client.view.resource.ApplicationResources;
import com.hibissscus.garage.client.view.resource.Messages;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Google gin module configuration
 *
 * @author hibissscus
 */
public class GwtWebAppGinModule extends AbstractGinModule {

    @Override
    protected void configure() {

        // Resources
        bind(Messages.class).in(Singleton.class);
        bind(ApplicationResources.class).in(Singleton.class);

        // Core
        bind(SimpleEventBus.class).in(Singleton.class);
        bind(WebAppController.class).in(Singleton.class);
        bind(ModelHandler.class).in(Singleton.class);

        // View
        bind(MainPanel.class).in(Singleton.class);
    }

}

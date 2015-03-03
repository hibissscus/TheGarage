package com.hibissscus.garage.client.gin;

import com.hibissscus.garage.client.controller.WebAppController;
import com.hibissscus.garage.client.model.ModelHandler;
import com.hibissscus.garage.client.view.MainPanel;
import com.hibissscus.garage.client.view.resource.ApplicationResources;
import com.hibissscus.garage.client.view.resource.Messages;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Google gin injector all components to inject.
 *
 * @author hibissscus
 */
@GinModules(GwtWebAppGinModule.class)
public interface GwtWebAppGinjector extends Ginjector {

    public SimpleEventBus getEventBus();

    public ApplicationResources getApplicationResources();

    public Messages getMessages();

    public WebAppController getWebAppController();

    public ModelHandler getModelHandler();

    public MainPanel getMainPanel();
}

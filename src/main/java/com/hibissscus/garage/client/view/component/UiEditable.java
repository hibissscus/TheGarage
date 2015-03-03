package com.hibissscus.garage.client.view.component;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Marker interface for editable objects.
 * 
 * @param <V>
 *            the type of the value to edit
 */
public interface UiEditable<V>
        extends HasValue<V>, HasKeyUpHandlers, ReadOnlyPresentable, Focusable {
    
   // empty marker interface
    
}
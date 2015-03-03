package com.hibissscus.garage.client.view.component;

/**
 * A widget that implements this interface changes its presentation if it is in
 * read-only mode.
 */
public interface ReadOnlyPresentable {

    /**
     * Returns if this widget is in read-only state.
     * 
     * @return if this widget is read-only
     */
    boolean isReadOnly();

    /**
     * Sets the state of a widget to read-only or writable.
     * 
     * @param readOnly
     *            is the widget read-only?
     */
    void setReadOnly(boolean readOnly);
}

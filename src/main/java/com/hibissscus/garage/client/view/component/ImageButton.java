package com.hibissscus.garage.client.view.component;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

/**
 * Custom component to extend simple button and add
 * resource image integration
 *
 * @author hibissscus
 */
public class ImageButton extends Button {
    private String text;

    public ImageButton() {
        super();
    }

    /**
     * reference to the image resource bundle
     *
     * @param imageResource image resources.
     */
    public void setResource(ImageResource imageResource) {
        Image img = new Image(imageResource);
        String definedStyles = img.getElement().getAttribute("style");
        img.getElement().setAttribute("style", definedStyles + "; vertical-align:middle;");
        DOM.insertChild(getElement(), img.getElement(), 0);
    }

    /**
     * text on the button
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setText(String text) {
        this.text = text;
        Element span = DOM.createElement("span");
        span.setInnerText(text);
        span.setAttribute("style", "padding-left:3px; vertical-align:middle;");
        DOM.insertChild(getElement(), span, 1);
    }

    @Override
    public String getText() {
        return this.text;
    }

}

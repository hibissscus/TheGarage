package com.hibissscus.garage.client.view.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

/**
 * Resource bundle
 * Gather images & css
 *
 * @author hibissscus
 */
public interface ApplicationResources extends ClientBundle {

    ApplicationResources RESOURCES = GWT.create(ApplicationResources.class);

    @Source("GwtWebAppStyles.css")
    public GwtWebAppStyles style();

    @Source("images/delete.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource deleteIcon();

    @Source("images/add.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource addIcon();

    @Source("images/clear.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource clearIcon();

    @Source("images/store.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource storeIcon();

    @Source("images/reload.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource reloadIcon();

    @Source("images/warning.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource warningIcon();

    @Source("images/theGarage.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource garageIcon();

    @Source("images/rubik.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource randomIcon();

    @Source("images/linkedin.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource linkedinIcon();

    @Source("images/github.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource githubIcon();


    // Spinner

    @Source("images/arrowDown.png")
    ImageResource arrowDownImage();

    @Source("images/arrowDownDisabled.png")
    ImageResource arrowDownDisabledImage();

    @Source("images/arrowDownHover.png")
    ImageResource arrowDownHoverImage();

    @Source("images/arrowDownPressed.png")
    ImageResource arrowDownPressedImage();

    @Source("images/arrowUp.png")
    ImageResource arrowUpImage();

    @Source("images/arrowUpDisabled.png")
    ImageResource arrowUpDisabledImage();

    @Source("images/arrowUpHover.png")
    ImageResource arrowUpHoverImage();

    @Source("images/arrowUpPressed.png")
    ImageResource arrowUpPressedImage();

}

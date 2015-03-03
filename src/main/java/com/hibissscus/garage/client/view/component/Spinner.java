package com.hibissscus.garage.client.view.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.hibissscus.garage.client.view.resource.ApplicationResources;

/**
 * This class provides a text box to enter a number and two arrows to in- or
 * decrease the value.
 */
public class Spinner extends Composite implements UiEditable<Long> {

    // ---------------------------------------------------------- Nested Types

    /**
     * The UIBinder interface for template binding.
     */
    interface Binder extends UiBinder<Widget, Spinner> {
        // empty at the moment
    }

    // --------------------------------------------------------- Static Fields

    /**
     * Abstract image factory for the arrow-up image.
     */
    private static final AbstractImagePrototype ARROW_UP = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowUpImage());

    /**
     * Abstract image factory for the arrow-up-disabled image.
     */
    private static final AbstractImagePrototype ARROW_UP_DISABLED = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowUpDisabledImage());

    /**
     * Abstract image factory for the arrow-up-hover image.
     */
    private static final AbstractImagePrototype ARROW_UP_HOVER = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowUpHoverImage());

    /**
     * Abstract image factory for the arrow-up-pressed image.
     */
    private static final AbstractImagePrototype ARROW_UP_PRESSED = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowUpPressedImage());

    /**
     * Abstract image factory for the arrow-down image.
     */
    private static final AbstractImagePrototype ARROW_DOWN = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowDownImage());

    /**
     * Abstract image factory for the arrow-down-disabled image.
     */
    private static final AbstractImagePrototype ARROW_DOWN_DISABLED = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowDownDisabledImage());

    /**
     * Abstract image factory for the arrow-down-hover image.
     */
    private static final AbstractImagePrototype ARROW_DOWN_HOVER = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowDownHoverImage());

    /**
     * Abstract image factory for the arrow-down-pressed image.
     */
    private static final AbstractImagePrototype ARROW_DOWN_PRESSED = AbstractImagePrototype
            .create(ApplicationResources.RESOURCES.arrowDownPressedImage());

    /**
     * The generated binder.
     */
    private static final Binder BINDER = GWT.create(Binder.class);

    /**
     * Value above which no zero must be prepended.
     */
    private static final int NO_ZERO_PREPENDING_NECESSARY = 10;

    // ------------------------------------------------------- Instance Fields

    /**
     * Image indicating increment.
     */
    @UiField
    Image arrowUpImage;

    /**
     * Image indicating decrement.
     */
    @UiField
    Image arrowDownImage;

    /**
     * Input field for the current value.
     */
    @UiField
    TextBox textBox;

    /**
     * Input field for the current value.
     */
    @UiField(provided = true)
    InlineLabel textLabel;

    /**
     * Current value.
     */
    private long currentValue;

    /**
     * If this spinner is enabled.
     */
    private boolean enabled = true;

    /**
     * Allowed maximum value.
     */
    private final long maxValue;

    /**
     * Allowed minimum value.
     */
    private final long minValue;

    // ---------------------------------------------------------- Constructors

    /**
     * Creates a new instance with the minimum and maximum value specified.
     *
     * @param min allowed minimum value
     * @param max allowed maximum value
     */
    public Spinner(long min, long max, String text) {
        this.minValue = min;
        this.maxValue = max;
        this.textLabel = new InlineLabel(text);
        initWidget(BINDER.createAndBindUi(this));
        this.currentValue = min;
        this.textBox.setText(String.valueOf(currentValue));
    }

    // ----------------------------------------------- Public Instance Methods

    /**
     * Get text box for spinner.
     *
     * @return text box
     */
    public TextBox getTextBox() {
        return textBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return textBox.addKeyUpHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addValueChangeHandler(
            ValueChangeHandler<Long> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Returns the current value as formatted string.
     *
     * @return current value as formatted string
     */
    public String getFormattedValue() {
        return textBox.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTabIndex() {
        return textBox.getTabIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getValue() {
        return currentValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadOnly() {
        return !enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccessKey(char key) {
        textBox.setAccessKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus(boolean focused) {
        textBox.setFocus(focused);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        this.enabled = !readOnly;
        if (enabled) {
            resetImages();
        } else {
            ARROW_UP_DISABLED.applyTo(arrowUpImage);
            ARROW_DOWN_DISABLED.applyTo(arrowDownImage);
        }
        textBox.setReadOnly(readOnly);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTabIndex(int index) {
        textBox.setTabIndex(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Long value) {
        setValue(value, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Long value, boolean fireEvents) {
        long newValue = 0;
        if (value != null) {
            newValue = value;
        }
        updateValue(newValue, fireEvents, true);
    }

    // --------------------------------------------- UIBinder Instance Methods

    /**
     * Handler which updates and formats the value of the text box if necessary.
     *
     * @param event to handle
     */
    @UiHandler("textBox")
    void doOnBlur(BlurEvent event) {
        updateValue(currentValue, false, true);
    }

    /**
     * Handler which updates the current value when a user clicks on an arrow
     * image.
     *
     * @param event to handle
     */
    @UiHandler({"arrowUpImage", "arrowDownImage"})
    void doOnClick(ClickEvent event) {
        boolean increase = ((Image) event.getSource() == arrowUpImage);
        updateValue(increase);
    }

    /**
     * Handler which updates the current value when a user presses the arrow-up
     * or arrow-down key.
     *
     * @param event to handle
     */
    @UiHandler("textBox")
    void doOnKeyDown(KeyDownEvent event) {
        int keyCode = event.getNativeEvent().getKeyCode();
        if (keyCode == KeyCodes.KEY_UP || keyCode == KeyCodes.KEY_DOWN) {
            boolean increase = (keyCode == KeyCodes.KEY_UP);
            updateValue(increase);
        }
    }

    /**
     * Updates the current value if the user enters a value manually, ignoring
     * some keys which are handled by other events.
     *
     * @param event to handle
     */
    @UiHandler("textBox")
    void doOnKeyUp(KeyUpEvent event) {
        boolean ignore = KeyCodeEvent.isArrow(event.getNativeKeyCode());

        if (enabled && !ignore) {
            long newValue;
            newValue = parseValue();

            if (currentValue != newValue) {
                currentValue = newValue;
                if (currentValue > maxValue) {
                    currentValue = maxValue;
                } else if (currentValue < minValue) {
                    currentValue = minValue;
                }
                // value is formatted on blur
                updateValue(currentValue, true, false);
            }
        }
    }

    /**
     * Updates the appearance of an image if a {@link MouseDownEvent} occurs.
     *
     * @param event to handle
     */
    @UiHandler({"arrowUpImage", "arrowDownImage"})
    void doOnMouseDown(MouseDownEvent event) {
        pressedImage((Image) event.getSource());
    }

    /**
     * Resets the images to their original appearance if a {@link MouseOutEvent}
     * occurs.
     *
     * @param event to handle
     */
    @UiHandler({"arrowUpImage", "arrowDownImage"})
    void doOnMouseOut(MouseOutEvent event) {
        resetImages();
    }

    /**
     * Updates the appearance of an image if a {@link MouseOverEvent} occurs.
     *
     * @param event to handle
     */
    @UiHandler({"arrowUpImage", "arrowDownImage"})
    void doOnMouseOver(MouseOverEvent event) {
        hoverImage((Image) event.getSource());
    }

    /**
     * Resets the images to their original appearance if a {@link MouseUpEvent}
     * occurs.
     *
     * @param event to handle
     */
    @UiHandler({"arrowUpImage", "arrowDownImage"})
    void doOnMouseUp(MouseUpEvent event) {
        resetImages();
    }

    /**
     * Propagates the event fired if the value of the text box changed.
     *
     * @param event to handle
     */
    @UiHandler("textBox")
    void doOnValueChange(ValueChangeEvent<String> event) {
        ValueChangeEvent.fire(Spinner.this, currentValue);
    }

    // -------------------------------------------- Protected Instance Methods

    /**
     * Updates the image specified if the user moved the mouse over this image.
     *
     * @param img to update
     */
    protected void hoverImage(Image img) {
        updateImage(img, ARROW_UP_HOVER, ARROW_DOWN_HOVER);
    }

    /**
     * Updates the image specified if the user pressed the mouse down over this
     * image.
     *
     * @param img to update
     */
    protected void pressedImage(Image img) {
        updateImage(img, ARROW_UP_PRESSED, ARROW_DOWN_PRESSED);
    }

    /**
     * Resets the images to their original appearance.
     */
    protected void resetImages() {
        if (enabled) {
            ARROW_UP.applyTo(arrowUpImage);
            ARROW_DOWN.applyTo(arrowDownImage);
        }
    }

    /**
     * Increases or decreases the current value depending on the flag specified.
     *
     * @param increase if the current value is increased
     */
    protected void updateValue(boolean increase) {
        if (enabled) {
            if (increase) {
                increase();
            } else {
                decrease();
            }
        }
    }

    // ---------------------------------------------- Private Instance Methods

    /**
     * Decreases the current value.
     */
    protected void decrease() {
        if (currentValue - 1 >= minValue) {
            currentValue--;
        } else {
            currentValue = maxValue;
        }
        updateValue(currentValue, true, true);
    }

    /**
     * Formats the value specified, means converts it to a string and prepends
     * zeros if necessary.
     *
     * @param value       to convert and format
     * @param prependZero if a zero should be prepended if necessary
     * @return formatted value
     */
    protected String formatValue(long value, boolean prependZero) {
//        if (prependZero && value < NO_ZERO_PREPENDING_NECESSARY) {
//            return "0" + value;
//        }
        return String.valueOf(value);
    }

    /**
     * Parses textBox value.
     *
     * @return -1 if parse process failed
     */
    protected long parseValue() {
        try {
            return Long.parseLong(textBox.getValue());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Increases the current value.
     */
    protected void increase() {
        if (currentValue + 1 <= maxValue) {
            currentValue++;
        } else {
            currentValue = minValue;
        }
        updateValue(currentValue, true, true);
    }

    /**
     * Updates the image specified depending on its purpose (in-/decrement) with
     * one of the prototypes specified.
     *
     * @param img                   to update
     * @param arrowUpImgPrototype   used if the images' purpose is incrementing
     * @param arrowDownImgPrototype used if the images' purpose is decrementing
     */
    private void updateImage(Image img,
                             AbstractImagePrototype arrowUpImgPrototype,
                             AbstractImagePrototype arrowDownImgPrototype) {
        if (enabled) {
            if (img.equals(arrowUpImage)) {
                arrowUpImgPrototype.applyTo(img);
            } else {
                arrowDownImgPrototype.applyTo(img);
            }
        }
    }

    /**
     * Updates the current value and the value of the text box using the value
     * specified, firing an event if required.
     *
     * @param value       to set
     * @param fireEvents  if an event should be fired
     * @param prependZero if a zero should be prepended to the value if necessary. This
     *                    is unwanted for example if the user enters a value manually
     */
    private void updateValue(long value, boolean fireEvents, boolean prependZero) {
        currentValue = value;
        textBox.setValue(formatValue(value, prependZero), fireEvents);
    }
}

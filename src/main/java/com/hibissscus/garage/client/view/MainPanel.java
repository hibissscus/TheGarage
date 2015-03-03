package com.hibissscus.garage.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.hibissscus.garage.client.model.ModelHandler;
import com.hibissscus.garage.client.view.component.ImageButton;
import com.hibissscus.garage.client.view.component.Spinner;
import com.hibissscus.garage.client.view.component.VehicleWidget;
import com.hibissscus.garage.client.view.resource.ApplicationResources;
import com.hibissscus.garage.shared.main.ParkingLevel;
import com.hibissscus.garage.shared.main.SpaceType;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;
import org.vectomatic.dom.svg.*;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGConstants;

/**
 * Main UI component
 *
 * @author hibissscus
 */
public class MainPanel extends Composite {

    public static final float ONE_SPACE = 60f;

    private static ApplicationResources resources = GWT.create(ApplicationResources.class);
    private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);


    /**
     * The interface Main panel ui binder.
     */
    interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
    }

	/*
     * UI components
	 */

    /**
     * Spinner to set levels.
     */
    @UiField(provided = true)
    Spinner levelSpinner;
    /**
     * Spinner to set rows.
     */
    @UiField(provided = true)
    Spinner rowSpinner;
    /**
     * Spinner to set columns.
     */
    @UiField(provided = true)
    Spinner columnSpinner;

    /**
     * A menu bar used to change levels.
     */
    @UiField
    ListBox levelBox;

    /**
     * Rebuild button.
     */
    @UiField
    ImageButton rebuildButton;

    /**
     * Clear button.
     */
    @UiField
    ImageButton randomButton;

    /**
     * Linkedin button.
     */
    @UiField
    ImageButton linkedinButton;

    /**
     * Github button.
     */
    @UiField
    ImageButton githubButton;

    /**
     * Vehicle panel.
     */
    @UiField
    FlowPanel vehiclePanel;

    /**
     * Logo.
     */
    @UiField(provided = true)
    Image logo;

    /**
     * SVG panel.
     */
    @UiField
    HTMLPanel svgContainer;

    /**
     * svg canvas
     */
    private OMSVGSVGElement svg;
    /**
     * svg doc
     */
    private OMSVGDocument doc;

    /**
     * event bus
     */
    private SimpleEventBus eventBus;

    /**
     * model
     */
    private ModelHandler modelHandler;

    /**
     * use isometry for svg
     */
    private boolean isometry = true;

    /**
     * button to chenge the view.
     */
    private OMSVGRectElement button;

    /**
     * Instantiates a new Main panel.
     *
     * @param eventBus     the event bus
     * @param modelHandler the model handler
     */
    @Inject
    public MainPanel(SimpleEventBus eventBus, ModelHandler modelHandler) {
        this.eventBus = eventBus;

        this.levelSpinner = new Spinner(1, 5, "Levels:");
        this.rowSpinner = new Spinner(1, 5, "Rows:");
        this.columnSpinner = new Spinner(1, 5, "Columns:");

        // init before bindUi
        this.logo = new Image(resources.garageIcon());

        // create a SVG document
        doc = OMSVGParser.currentDocument();
        svg = doc.createSVGSVGElement();
        svg.setViewBox(0f, 0f, 530f, 500f);
        svg.getWidth().getBaseVal().newValueSpecifiedUnits(Style.Unit.PCT, 100);
        svg.getHeight().getBaseVal().newValueSpecifiedUnits(Style.Unit.PCT, 100);

        // init display
        initWidget(uiBinder.createAndBindUi(this));
        Element div = svgContainer.getElement();
        div.appendChild(svg.getElement());

        this.modelHandler = modelHandler;

        button = doc.createSVGRectElement(480f, 1f, 20f, 20f, 10f, 10f);
        button.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, SVGConstants.CSS_LIGHTGRAY_VALUE);
        button.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
        button.getStyle().setSVGProperty(SVGConstants.CSS_CURSOR_PROPERTY, SVGConstants.CSS_POINTER_VALUE);
        button.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                isometry = !isometry;
                showTheGarage();
            }
        });
        svg.appendChild(button);

        randomValues();
        init();
    }

    /**
     * Main SVG method to draw TheGarage.
     */
    public void showTheGarage() {
        for (OMNode omNode : svg.getChildNodes()) {
            if (omNode != button) svg.removeChild(omNode);
            svg.forceRedraw();
        }

        OMSVGTextElement freeSpace = doc.createSVGTextElement(370f, 15f,
                OMSVGLength.SVG_LENGTHTYPE_PX, "free spaces: " + modelHandler.getGarage().freeSpaceAvailable());
        freeSpace.getStyle().setSVGProperty(SVGConstants.CSS_FONT_SIZE_PROPERTY, String.valueOf(14));

        OMSVGTextElement cannotPark = doc.createSVGTextElement(370f, 35f,
                OMSVGLength.SVG_LENGTHTYPE_PX, "can not park!");
        cannotPark.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_RED_VALUE);
        cannotPark.getStyle().setSVGProperty(SVGConstants.CSS_FONT_SIZE_PROPERTY, String.valueOf(14));


        // Transform
        OMSVGMatrix svgMatrix = svg.createSVGMatrix();
        svgMatrix.setA(0.707f);
        svgMatrix.setB(0.409f);
        svgMatrix.setC(-0.707f);
        svgMatrix.setD(0.409f);
        svgMatrix.setE(0f);
        svgMatrix.setF(-0.816f);
        OMSVGTransform transform1 = svg.createSVGTransform();
        if (isometry)
            transform1.setTranslate(350f, 30f);

        OMSVGTransform transform2 = svg.createSVGTransform();
        if (isometry)
            transform2.setMatrix(svgMatrix);

        OMSVGTransform transform3 = svg.createSVGTransform();
        if (isometry)
            transform3.setTranslate(-100f, 80f);

        OMSVGGElement transform = doc.createSVGGElement();
        transform.setAttribute("transform", transform1.getDescription());
        OMSVGGElement transformElement2 = doc.createSVGGElement();
        transformElement2.setAttribute("transform", transform2.getDescription());
        OMSVGGElement transformElement3 = doc.createSVGGElement();
        transformElement3.setAttribute("transform", transform3.getDescription());
        transform.appendChild(transformElement2);
        transformElement2.appendChild(transformElement3);

        OMSVGGElement level = doc.createSVGGElement();


        for (ParkingLevel parkingLevel : modelHandler.getGarage().getParkingLevels()) {

            // selected
            if (levelBox.getSelectedIndex() >= 0) {
                parkingLevel = modelHandler.getGarage().getParkingLevels()[levelBox.getSelectedIndex()];
            }

            OMSVGRectElement floor =
                    doc.createSVGRectElement(20f, 1f, parkingLevel.getColumn() * ONE_SPACE, parkingLevel.getRow() * ONE_SPACE, 0f, 0f);
            floor.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, SVGConstants.CSS_LIGHTGREEN_VALUE);
            floor.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
            level.appendChild(floor);

            for (int r = 0; r < parkingLevel.getRow(); r++) {
                for (int c = 0; c < parkingLevel.getColumn(); c++) {
                    OMSVGRectElement space =
                            doc.createSVGRectElement(20f + c * ONE_SPACE, 1f + r * ONE_SPACE, ONE_SPACE, ONE_SPACE, 10f, 10f);
                    space.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);

                    if (parkingLevel.getSpaces()[r][c].getType() == SpaceType.FREE) {
                        space.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, SVGConstants.CSS_LIGHTGREEN_VALUE);
                    } else {
                        space.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, SVGConstants.CSS_LIGHTCORAL_VALUE);
                    }
                    level.appendChild(space);
                }
            }

            OMSVGTextElement levelNumber = doc.createSVGTextElement(35f, 50f,
                    OMSVGLength.SVG_LENGTHTYPE_PX, String.valueOf(parkingLevel.getLevel()));
            levelNumber.getStyle().setSVGProperty(SVGConstants.CSS_FONT_SIZE_PROPERTY, String.valueOf(55));
            level.appendChild(levelNumber);
        }
        transformElement3.appendChild(level);

        // combine into group
        OMSVGGElement group = doc.createSVGGElement();
        group.appendChild(freeSpace);
        if (modelHandler.getGarage().isCannotPark()) {
            group.appendChild(cannotPark);
        }
        group.appendChild(transform);

        // insert to SVG root
        svg.appendChild(group);
    }

    /**
     * On levelBox change.
     *
     * @param e the e
     */
    @UiHandler("levelBox")
    void onLevelBoxChange(com.google.gwt.event.dom.client.ChangeEvent e) {
        levelBox.getSelectedIndex();
        showTheGarage();
    }

    /**
     * On clear button click.
     *
     * @param e the e
     */
    @UiHandler("randomButton")
    void onClearButtonClick(ClickEvent e) {
        randomValues();
    }

    /**
     * Random spinner values.
     */
    private void randomValues() {
        levelSpinner.setValue((long) Random.nextInt(5) + 1);
        rowSpinner.setValue((long) Random.nextInt(5) + 1);
        columnSpinner.setValue((long) Random.nextInt(5) + 1);
    }

    /**
     * On load button click.
     *
     * @param e the e
     */
    @UiHandler("rebuildButton")
    void onRebuildButtonClick(ClickEvent e) {
        init();
    }

    /**
     * Re-init.
     */
    private void init() {
        this.modelHandler.newRandomList();
        this.modelHandler.newTheGarage(
                levelSpinner.getValue().intValue(),
                rowSpinner.getValue().intValue(),
                columnSpinner.getValue().intValue());
        levelBox.clear();

        for (int i = 0; i < this.modelHandler.getGarage().getLevels(); i++) {
            levelBox.addItem("Level: " + i);
        }

        reloadTable();
        showTheGarage();
    }

    /**
     * On linkedin button click.
     *
     * @param e the e
     */
    @UiHandler("linkedinButton")
    void onLinkedinClick(ClickEvent e) {
        Window.Location.assign("https://www.linkedin.com/in/sergeistepanov");
    }

    /**
     * On github button click.
     *
     * @param e the e
     */
    @UiHandler("githubButton")
    void onGithubClick(ClickEvent e) {
        Window.Location.assign("https://github.com/hibissscus/TheGarage");
    }

    /**
     * Add vehicle to panel.
     *
     * @param Vehicle the Vehicle
     */
    public void addVehicleToPanel(Vehicle Vehicle) {
        VehicleWidget w = new VehicleWidget(Vehicle, eventBus);
        vehiclePanel.add(w);

    }

    /**
     * Remove all.
     */
    public void removeAll() {
        vehiclePanel.clear();
    }

    /**
     * Retrieve new model and schedule the command call.
     */
    public void reloadTable() {
        removeAll();
        for (Vehicle vehicle : modelHandler.getVehicles()) {
            addVehicleToPanel(vehicle);
        }
    }
}

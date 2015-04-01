package com.hibissscus.garage.server.jasper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Jasper datasource
 *
 * @author hibissscus
 */
@Service
public class JasperDatasourceService {


    /**
     * Returns a data source that's wrapped within {@link JRDataSource}
     */
    public JRDataSource getDataSource() {
        List<JasperVehicle> jasperVehicles = new ArrayList<JasperVehicle>();

        JasperVehicle jasperVehicle0 = new JasperVehicle();
        jasperVehicle0.setIconUrl("http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/bmw-mini-icon.png");
        jasperVehicle0.setLicensePlate("6A1256FED5E2");
        jasperVehicle0.setParkingPosition("parking position: level[0] [0,0][0,1]");
        jasperVehicles.add(jasperVehicle0);

        JasperVehicle jasperVehicle1 = new JasperVehicle();
        jasperVehicle1.setIconUrl("http://megaicons.net/static/img/icons_sizes/319/820/64/vw-beetle-icon.png");
        jasperVehicle1.setLicensePlate("966B7872E088");
        jasperVehicle1.setParkingPosition("parking position: level[0] [1,0][1,1];");
        jasperVehicles.add(jasperVehicle1);


        JasperVehicle jasperVehicle2 = new JasperVehicle();
        jasperVehicle2.setIconUrl("http://icons.iconarchive.com/icons/cemagraphics/classic-cars/64/yellow-pickup-icon.png");
        jasperVehicle2.setLicensePlate("B165051B2C2C");
        jasperVehicle2.setParkingPosition("parking position: level[0] [2,0][2,1];");
        jasperVehicles.add(jasperVehicle2);


        // Return wrapped collection
        return new JRBeanCollectionDataSource(jasperVehicles);
    }
}
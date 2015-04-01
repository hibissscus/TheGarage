package com.hibissscus.garage.server.jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Download Service for jasper report.
 *
 * @author hibissscus
 */
@Service
public class DownloadService {

    public static final String TEMPLATE = "garage.jasper";
    protected static Logger LOGGER = LoggerFactory.getLogger(DownloadService.class);

    @Autowired
    private JasperDatasourceService datasource;

    @Autowired
    private ExporterService exporter;

    public void download(String type, HttpServletResponse response) {

        try {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("Title", "TheGarage");

//            InputStream reportStream = this.getClass().getResourceAsStream(TEMPLATE);
//            JasperDesign jd = JRXmlLoader.load(reportStream);
//            JasperReport jr = JasperCompileManager.compileReport(jd);

            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(TEMPLATE);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource.getDataSource());

            // Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Export report
            exporter.export("pdf", jp, response, baos);

            // Write to reponse stream
            write(response, baos);

        } catch (JRException jre) {
            LOGGER.error("Unable to process download");
            throw new RuntimeException(jre);
        }
    }

    /**
     * Writes the report to the output stream
     */
    private void write(HttpServletResponse response,
                       ByteArrayOutputStream baos) {

        try {
            // Retrieve output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to output stream
            baos.writeTo(outputStream);
            // Flush the stream
            outputStream.flush();

        } catch (Exception e) {
            LOGGER.error("Unable to write report to the output stream");
            throw new RuntimeException(e);
        }
    }
}
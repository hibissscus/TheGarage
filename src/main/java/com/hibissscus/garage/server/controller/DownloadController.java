package com.hibissscus.garage.server.controller;

import com.hibissscus.garage.server.jasper.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * New Class
 *
 * @author hibissscus
 */
@Controller
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    /**
     * PDF download.
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
        downloadService.download("pdf", response);
    }
}

package com.hibissscus.garage.server.controller;

import com.hibissscus.garage.server.dao.AddressIPRepository;
import com.hibissscus.garage.server.entity.AddressIP;
import com.hibissscus.garage.shared.model.IpAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * The Uptime controller.
 *
 * @author hibissscus
 */
@RestController
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    /**
     * The Ips.
     */
    @Autowired
    AddressIPRepository ips;

    /**
     * Version string.
     *
     * @return the string
     */
    @RequestMapping(value = "/rest/version", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String version() {
        return "14.03.03.00";
    }

    /**
     * Application uptime string.
     *
     * @return the string
     * @throws Exception the exception
     */
    @RequestMapping(value = "/rest/uptime", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String uptime() throws Exception {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long ms = rb.getUptime();

        final int SECOND = 1000;
        final int MINUTE = 60 * SECOND;
        final int HOUR = 60 * MINUTE;
        final int DAY = 24 * HOUR;

        StringBuffer uptime = new StringBuffer("");
        if (ms > DAY) {
            uptime.append(ms / DAY).append(" days ");
            ms %= DAY;
        }
        if (ms > HOUR) {
            uptime.append(ms / HOUR).append(" hours ");
            ms %= HOUR;
        }
        if (ms > MINUTE) {
            uptime.append(ms / MINUTE).append(" minutes ");
            ms %= MINUTE;
        }
        if (ms > SECOND) {
            uptime.append(ms / SECOND).append(" seconds ");
            ms %= SECOND;
        }
        uptime.append(ms).append(" ms");

        return String.valueOf(uptime);
    }

    /**
     * Ip address.
     *
     * @return the string
     * @throws Exception the exception
     */
    @RequestMapping(value = "/rest/ip", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ipAddress() throws Exception {
        String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        RestTemplate restTemplate = new RestTemplate();
        IpAddress address = restTemplate.getForObject("http://ip-api.com/json/" + ip, IpAddress.class);

        if (ips.findByAddress(ip).size() == 0) {
            AddressIP ipAddress = new AddressIP();
            ipAddress.setAddress(ip);
            ipAddress.setCountry(address.getCountry());
            ips.save(ipAddress);
        }
        return ip;
    }
}

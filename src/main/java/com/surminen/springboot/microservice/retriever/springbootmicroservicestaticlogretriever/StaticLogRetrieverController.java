package com.surminen.springboot.microservice.retriever.springbootmicroservicestaticlogretriever;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 */
@RestController
public class StaticLogRetrieverController
{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogDbServiceProxy proxy;

    /**
     * TODO
     * 
     * @param from
     * @param logData
     * @return TODO
     */
    @GetMapping("/currency-converter-feign/from/{from}/logdata/{logData}")
    public LogDataBean convertCurrencyFeign(@PathVariable String from, @PathVariable String logData)
    {
        LogDataBean[] response = proxy.retrieveLogValue(from);

        logger.info("{}", response[0]);

        proxy.putLogValue(new LogDataBean(decodeDate("11_11_2016"), "statically added log text"));

        return new LogDataBean(decodeDate(from), response[0].getLog());
    }

    /**
     * TODO
     * 
     * @param date
     * @return TODO
     */
    private LocalDate decodeDate(String date)
    {
        // return from.replace("_", "/");
        System.out.println("Converting: " + date);
        // Date x = new SimpleDateFormat("dd/MM/yyyy").parse(date.replace("_", "/"));
        LocalDate y = LocalDate.parse(date.replace("_", "/"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return y;
    }
}
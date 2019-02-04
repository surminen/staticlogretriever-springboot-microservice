package com.surminen.springboot.microservice.retriever.springbootmicroservicestaticlogretriever;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
    @GetMapping("/add-log/from/{from}/logdata/{logData}")
    public String addLog(@PathVariable String from, @PathVariable String logData)
    {
        LogDataBean log = new LogDataBean(decodeDate(from), logData);
        proxy.putLogValue(log);

        return "New log added:<br>" + log;
    }

    /**
     * TODO
     * 
     * @return TODO
     */
    @GetMapping("/add-random-log")
    public String addRandomLog()
    {
        Random rnd = new Random(System.currentTimeMillis());

        // TODO obviously fix this to be able to include later days.
        int day = rnd.nextInt(27) + 1;
        int month = rnd.nextInt(11) + 1;
        int year = rnd.nextInt(500) + 2000;

        long logValue = rnd.nextLong();

        LogDataBean log = new LogDataBean(decodeDate(String.format("%02d", day) + "_" + String.format("%02d", month) + "_" + year), "statically added log text #" + logValue);
        proxy.putLogValue(log);

        return "Random log added:<br>" + log;
    }

    /**
     * TODO
     * 
     * @param date
     * @return TODO
     */
    private LocalDate decodeDate(String date)
    {
        System.out.println("Converting: " + date);
        LocalDate localDate = LocalDate.parse(date.replace("_", "/"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return localDate;
    }
}
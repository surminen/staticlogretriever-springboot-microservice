package com.surminen.springboot.microservice.retriever.springbootmicroservicestaticlogretriever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO
 */
@SpringBootApplication
@EnableFeignClients("com.surminen.springboot.microservice.retriever.springbootmicroservicestaticlogretriever")
@EnableDiscoveryClient
public class SpringBootMicroserviceStaticLogRetrieverApplication
{

    /**
     * TODO 
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootMicroserviceStaticLogRetrieverApplication.class, args);
    }

}

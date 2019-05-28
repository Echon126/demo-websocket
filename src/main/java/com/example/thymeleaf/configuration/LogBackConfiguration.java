package com.example.thymeleaf.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class LogBackConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(LogBackConfiguration.class);

    @PostConstruct
    public void init() {
        logger.debug("----debugger-------" + new Date());
        logger.info("----info-------" + new Date());
        logger.error("----error-------" + new Date());
        logger.warn("----warn-------" + new Date());
        logger.trace("----trace-------" + new Date());
    }

}

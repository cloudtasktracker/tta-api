package com.tasktracker.api.starter;

import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import com.tasktracker.api.config.AppConf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TaskTrackerAPIStarter {

    private final static Logger logger = Logger.getLogger("Starter");
    private final static String PORT = System.getenv("TTA_API_CONF").equals("wc")?System.getenv("TTA_API_PORT"):System.getenv("PORT");
    private static final String BASE_URI = "http://localhost:"+PORT+"/";
    protected final static String SERVICES_PACKAGE = "com.tasktracker.api.server";

    public static void main(String[] args) throws IOException {
        new AppConf();
        Map<String, String> initParams = new HashMap<>();
        initParams.put("com.sun.jersey.config.property.packages",SERVICES_PACKAGE);
        logger.info("----------- TTA-API: Grizzly Server starter at: "+BASE_URI);
        GrizzlyWebContainerFactory.create(BASE_URI, initParams);
    }

}

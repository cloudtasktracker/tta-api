package com.tasktracker.api.config;

import com.tasktracker.libraries.common.logging.LoggingCodes;
import com.tasktracker.libraries.common.logging.OperationLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * Created by u6019943 on 08/07/2015.
 */
public class AppConf {

    public static Properties taskServiceConf;
    
    public AppConf(){
        taskServiceConf = new Properties();
        try(InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf"+ File.separator+System.getenv("TTA_API_CONF")+ File.separator+"tts.task.properties")) {
            taskServiceConf.load(resourceStream);
        } catch (IOException e) {
            OperationLogger operationLogger = new OperationLogger(LoggingCodes.TTA_API_LOADING_CONFIG.getCode().replace("%1", e.getMessage()),true);
            operationLogger.operationFinished();
        }        
    }
}

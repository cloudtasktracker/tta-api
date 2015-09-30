package com.tasktracker.api.client;

import com.tasktracker.api.config.AppConf;
import com.tasktracker.api.operation.TaskOperation;
import com.tasktracker.libraries.common.client.RestClient;
import com.tasktracker.libraries.common.logging.LoggingCodes;
import com.tasktracker.libraries.common.logging.OperationLogger;
import com.tasktracker.libraries.common.utils.StringUtils;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by joan on 5/7/15.
 */
public class TaskServiceClient extends RestClient {

    public String listTask(String userId){
        List<String> params = new ArrayList<>();
        params.add(userId);

        return super.doGet(this.createTaskOperationUrl(TaskOperation.LIST_TASK, params));
    }

    public String getTask(String name){
        List<String> params = new ArrayList<>();
        params.add(name);

        return super.doGet(this.createTaskOperationUrl(TaskOperation.GET_TASK, params));
    }

    public String addTask(String name){
        List<String> params = new ArrayList<>();
        params.add(name);

        return super.doPost(this.createTaskOperationUrl(TaskOperation.ADD_TASK, params));
    }

    public String deleteTask(String taskId){
        List<String> params = new ArrayList<>();
        params.add(taskId);

        return super.doDelete(this.createTaskOperationUrl(TaskOperation.DELETE_TASK, params));
    }

    public String updateTask(String userId, String taskId){
        List<String> params = new ArrayList<>();
        params.add(userId);
        params.add(taskId);

        return super.doPut(this.createTaskOperationUrl(TaskOperation.UPDATE_TASK, params));
    }

    private String createTaskOperationUrl(TaskOperation operation, List<String> params){
        String host = System.getenv("TTA_API_CONF").equals("wc")? AppConf.taskServiceConf.getProperty("service.task.host")+":":AppConf.taskServiceConf.getProperty("service.task.host");
        String port = System.getenv("TTA_API_CONF").equals("wc")?AppConf.taskServiceConf.getProperty("service.task.port"):"";
        String endpoint = AppConf.taskServiceConf.getProperty("service.task.endpoint");

        try{
            switch(operation){
                case LIST_TASK:
                    return URIUtil.encodeQuery(host + port + endpoint + TaskOperation.LIST_TASK.getOperation() + StringUtils.createPathParams(params));
                case GET_TASK:
                    return URIUtil.encodeQuery(host + port + endpoint + TaskOperation.GET_TASK.getOperation() + StringUtils.createPathParams(params));
                case ADD_TASK:
                    return URIUtil.encodeQuery(host+port+endpoint+TaskOperation.ADD_TASK.getOperation()+StringUtils.createPathParams(params));
                case DELETE_TASK:
                    return URIUtil.encodeQuery(host+port+endpoint+TaskOperation.DELETE_TASK.getOperation()+StringUtils.createPathParams(params));
                case UPDATE_TASK:
                    return URIUtil.encodeQuery(host+port+endpoint+TaskOperation.UPDATE_TASK.getOperation()+StringUtils.createPathParams(params));
                default:
                    return "";
            }
        } catch (URIException e){
            OperationLogger operationLogger = new OperationLogger(LoggingCodes.TTA_API_CREATE_URI_EXCEPTION.getCode().replace("%1", e.getMessage()),true);
            operationLogger.operationFinished();
        }
        return "";
    }
}

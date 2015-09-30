package com.tasktracker.api.server;

import com.tasktracker.api.client.TaskServiceClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by joan on 4/7/15.
 */
@Path("/api")
public class TaskTrackerAPI {

    private TaskServiceClient taskServiceClient;

    public TaskTrackerAPI(){
        this.taskServiceClient = new TaskServiceClient();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Task Tracker API - Alive";
    }

    @GET
    @Path("/task/list/{userId: .*}")
    @Produces("application/json")
    public String listTask(@PathParam("userId") String userId) {
        return this.taskServiceClient.listTask(userId);
    }

    @GET
    @Path("task/get/{taskId: .*}")
    @Produces("application/json")
    public String getTask(@PathParam("taskId") String taskId) {
        return this.taskServiceClient.getTask(taskId);
    }

    @POST
    @Path("task/add/{name}")
    @Produces("application/json")
    public String addTask(@PathParam("name") String name) {
        return this.taskServiceClient.addTask(name);
    }

    @DELETE
    @Path("task/delete/{taskId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteTask(@PathParam("taskId") String taskId) {
        return this.taskServiceClient.deleteTask(taskId);
    }

    @PUT
    @Path("task/do/{userId}/{taskId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateTask(@PathParam("userId") String userId, @PathParam("taskId") String taskId) {
        return this.taskServiceClient.updateTask(userId, taskId);
    }
}

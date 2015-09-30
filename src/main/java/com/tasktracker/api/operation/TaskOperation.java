package com.tasktracker.api.operation;

/**
 *
 * Created by joan on 5/7/15.
 */
public enum TaskOperation {
    LIST_TASK("list"),
    GET_TASK("get"),
    ADD_TASK("add"),
    UPDATE_TASK("do"),
    DELETE_TASK("delete");

    private final String operation;

    TaskOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}

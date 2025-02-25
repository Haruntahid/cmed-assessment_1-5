package com.health.cmed_task_01.exceptions;

public class ResourceAlreadyExist extends RuntimeException {
    public ResourceAlreadyExist(String message) {
        super(message);
    }
}

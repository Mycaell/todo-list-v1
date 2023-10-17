package com.studies.todolist.domain.shared.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " not found!");
        this.resourceName = resourceName;
    }
}

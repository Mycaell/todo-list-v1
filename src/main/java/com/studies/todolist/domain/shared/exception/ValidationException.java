package com.studies.todolist.domain.shared.exception;

public class ValidationException extends RuntimeException {

    private String validationMessage;

    public ValidationException() {
        super("Invalid data!");
    }

    public ValidationException(String validationMessage) {
        super(validationMessage + "!");
        this.validationMessage = validationMessage;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}

package com.storage.model;

import java.util.List;

public class ExceptionMessage {

    private String title;
    private String message;
    private List<ExceptionMessage> errors;

    public ExceptionMessage(String title, String message, List<ExceptionMessage> errors) {
        this.title = title;
        this.message = message;
        this.errors = errors;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public List<ExceptionMessage> getErrors() {
        return errors;
    }
}

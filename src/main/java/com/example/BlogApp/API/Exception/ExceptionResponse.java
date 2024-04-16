package com.example.BlogApp.API.Exception;

import java.sql.Timestamp;
import java.util.Date;

public class ExceptionResponse {
    String message, details ;
    Date timestamp ;

    public ExceptionResponse(String message, String details, Date timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

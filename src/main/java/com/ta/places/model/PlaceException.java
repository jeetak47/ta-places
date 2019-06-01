package com.ta.places.model;

public class PlaceException extends Exception {
    private String errorMessage;
    private String errorCode;

    public PlaceException(String errorMessage,String errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode=errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

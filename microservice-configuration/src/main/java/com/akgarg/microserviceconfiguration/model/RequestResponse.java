package com.akgarg.microserviceconfiguration.model;

@SuppressWarnings("unused")
public class RequestResponse {

    private String message;
    private int code;

    public RequestResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public RequestResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RequestResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}

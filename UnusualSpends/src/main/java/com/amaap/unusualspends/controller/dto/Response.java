package com.amaap.unusualspends.controller.dto;

import java.util.Objects;

public class Response {
    private HttpStatus httpStatus;
    private String message;
    public Response(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return httpStatus == response.httpStatus && Objects.equals(message, response.message);
    }
}

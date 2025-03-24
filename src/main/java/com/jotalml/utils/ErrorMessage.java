package com.jotalml.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private String detail;

    public ErrorMessage(Exception exception) {
        this.message = exception.getClass().getSimpleName();
        this.detail = exception.getMessage();
    }

    public ErrorMessage(Exception exception, String detail) {
        this.message = exception.getClass().getSimpleName();
        this.detail = detail;
	}

    public ErrorMessage(String message, String detail) {
        this.message = message;
        this.detail = detail;
	}
}

package com.jotalml.utils;

import java.util.List;

public class ListErrorException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public List<Exception> exceptionList;

    public ListErrorException(List<Exception> error) {
        this.exceptionList = error;
    }
}


package com.chunjae.test05.exception;

public class DataIntefrityViolationException extends RuntimeException {
    public DataIntefrityViolationException(String messsage){
        super(messsage);
    }
}

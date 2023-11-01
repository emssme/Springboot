package com.chunjae.test03.exception;

public class DataIntefrityViolationException extends RuntimeException {
    public DataIntefrityViolationException(String messsage){
        super(messsage);
    }
}

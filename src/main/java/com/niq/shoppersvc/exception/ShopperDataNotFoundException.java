package com.niq.shoppersvc.exception;

public class ShopperDataNotFoundException extends RuntimeException {
    public ShopperDataNotFoundException(String message){
        super(message);
    }
}

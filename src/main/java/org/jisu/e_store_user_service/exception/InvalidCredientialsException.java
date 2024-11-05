package org.jisu.e_store_user_service.exception;

public class InvalidCredientialsException extends RuntimeException{
    
    public InvalidCredientialsException(){
        super("Invalid Credientials");
    }
}

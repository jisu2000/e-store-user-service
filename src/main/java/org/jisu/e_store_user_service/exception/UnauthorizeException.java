package org.jisu.e_store_user_service.exception;

public class UnauthorizeException extends RuntimeException{
    
    public UnauthorizeException(){
        super("Request has been blocked due to Unauthenticed source");
    }

    public UnauthorizeException(String msg){
        super(msg);
    }

}

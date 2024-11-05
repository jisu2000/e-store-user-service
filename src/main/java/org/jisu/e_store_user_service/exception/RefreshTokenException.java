package org.jisu.e_store_user_service.exception;

public class RefreshTokenException extends RuntimeException{
    public RefreshTokenException(){
        super("Invalid Token");
    }

    public RefreshTokenException(String msg){
        super(msg);
    }
}

package org.jisu.e_store_user_service.exception;

public class InvalidOtpException extends RuntimeException{
    
    public InvalidOtpException(){
        super("Invalid OTP");
    }
    public InvalidOtpException(String msg){
        super(msg);
    }
}

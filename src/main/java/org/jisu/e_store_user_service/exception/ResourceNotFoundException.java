package org.jisu.e_store_user_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Entity not found");
    }

    public ResourceNotFoundException(String entity, String fieldName, String fieldValue) {

        super(entity + " " + "Not found with " + fieldName + " " + fieldValue);
    }
}

package br.com.dscommerce.Course.infraestrutura.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String MSG){
        super(MSG);
    }
}

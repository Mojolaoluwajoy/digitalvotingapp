package org.app.digitalVotingApp.data.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericResponse {
    private boolean success;
    private String message;
    private Object data;

    public static  GenericResponse failed(String message){
        GenericResponse response=new GenericResponse();
        response.setSuccess(false);
        response.setMessage(message);
         return response;
    }
    public static GenericResponse success(Object data,String message){
         GenericResponse response=new GenericResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}

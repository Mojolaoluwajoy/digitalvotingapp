package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.UserRequest;
import org.app.digitalVotingApp.data.dtos.responses.UserResponse;
import org.app.digitalVotingApp.data.model.User;

import java.util.UUID;

public class UserMap {
    public static User map(UserRequest request){
        User user=new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());
        user.setNin(request.getNin());
        return user;
    }
    public static UserResponse map(User user){
        UserResponse response=new UserResponse();
         response.setUserId(user.getUserId());
         response.setFirstName(user.getFirstName());
         response.setLastName(user.getLastName());
         response.setEmail(user.getEmail());
         response.setRole(user.getRole());
         response.setStatus(user.getStatus());
          return response;
    }
}

package org.app.digitalVotingApp.data.dtos.requests;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.app.digitalVotingApp.data.enums.Role;
import org.app.digitalVotingApp.data.enums.Status;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserRequest {

      private String firstName;
     private String lastName;
      private String nin;
    private String password;
     private String email;
      private Role role;
      private Status status;

}

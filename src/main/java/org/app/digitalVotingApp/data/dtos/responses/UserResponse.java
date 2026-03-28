package org.app.digitalVotingApp.data.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.app.digitalVotingApp.data.enums.Role;
import org.app.digitalVotingApp.data.enums.Status;

@Setter
@Getter
public class UserResponse {
private String userId;
    private String email;
    private Role role;
    private Status status;
    private String firstName;
    private String lastName;

}

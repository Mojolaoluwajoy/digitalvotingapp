package org.app.digitalVotingApp.data.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VotersRegistrationRequest {
    private String firstName;
    private String lastName;
    private String nin;
    private String password;

}

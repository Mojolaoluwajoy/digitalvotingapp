package org.app.digitalVotingApp.data.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CandidateRegistrationRequest {
    private String firstName;
    private String lastName;
    private String partyName;
    private String nin;


}

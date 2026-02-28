package org.app.digitalVotingApp.data.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CandidatesResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String partyName;
    private int voteCount;

}

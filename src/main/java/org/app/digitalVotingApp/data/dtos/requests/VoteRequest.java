package org.app.digitalVotingApp.data.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoteRequest {
    private String voterNin;
    private String candidateId;


}

package org.app.digitalVotingApp.data.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoteRespose {
    private String voteId;
    private String candidateId;
private String voterId;

}

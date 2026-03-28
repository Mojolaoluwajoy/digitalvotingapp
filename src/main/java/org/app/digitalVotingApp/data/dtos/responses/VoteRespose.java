package org.app.digitalVotingApp.data.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VoteRespose {
    private String voteId;
    private String candidateId;
private String votersId;
    private LocalDateTime time;


}

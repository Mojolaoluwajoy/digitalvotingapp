package org.app.digitalVotingApp.data.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VoteRequest {
    private String voterNin;
    private String candidatePublicId;


}

package org.app.digitalVotingApp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Candidates {
    private String id;
    private String firstName;
    private String lastName;
    private String partyName;
    private String nin;
    private int voteCount;

    public void incrementVote(){
        voteCount=1+getVoteCount();
    }


}
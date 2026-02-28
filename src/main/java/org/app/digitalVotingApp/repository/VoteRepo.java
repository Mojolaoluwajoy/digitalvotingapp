package org.app.digitalVotingApp.repository;

import org.app.digitalVotingApp.exceptions.AlreadyVotedException;
import org.app.digitalVotingApp.model.Vote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class VoteRepo {
    private List<Vote> votesList=new ArrayList<>();

    public Vote save(Vote vote){
        votesList.add(vote);
        return vote;
    }

    public  List <Vote> getAll(){
        return votesList;
    }

public void oneVotePerVoter(String nin)throws  AlreadyVotedException{
        for (Vote vote:votesList){
            if (vote.getVoterNin().equals(nin)){
              throw new AlreadyVotedException("The voter with this nin has already voted!") ;
            }
        }
}
}

package org.app.digitalVotingApp.repository;

import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.model.Voters;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class VotersRepository {
 private List <Voters> votersList=new ArrayList<>();

public Voters addUser(Voters user){
    votersList.add(user);
    return user;
}

    public List<Voters> findAll() {
        return votersList;
    }

    public Voters findById(String voterId){
    for (Voters voter: votersList){
        if (voter.getVotersId().equalsIgnoreCase(voterId)){
            return voter;
        }
    }
    return  null;
    }
    public Voters findByNin(String nin) {
        for (Voters voter : findAll()) {
            if (voter.getNin().equals(nin)) {
                return voter;
            }
        }
        return null;
    }
    public void validateUserExistenceByNin(String nin) throws VoterAlreadyExistException {
        Voters foundUser = this.findByNin(nin);

        if (foundUser != null) {
            throw new VoterAlreadyExistException(String.format("Voter with this nin  already exist" ));
        }
       // return ("Voter is eligible to vote");


    }
}

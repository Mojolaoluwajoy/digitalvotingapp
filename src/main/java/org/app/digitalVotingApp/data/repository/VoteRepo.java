package org.app.digitalVotingApp.data.repository;

import org.app.digitalVotingApp.data.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepo extends JpaRepository<Vote,Long> {


    Optional<Vote> findByVoterNin(String voterNin);
}

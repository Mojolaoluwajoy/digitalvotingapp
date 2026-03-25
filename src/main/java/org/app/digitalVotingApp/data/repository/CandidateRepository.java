package org.app.digitalVotingApp.data.repository;

import org.app.digitalVotingApp.data.model.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateProfile,Long> {

}

package org.app.digitalVotingApp.data.repository;

import org.app.digitalVotingApp.data.model.Voters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VotersRepository extends JpaRepository<Voters,Long> {



    Optional <Voters> findByVotersId(String votersId);

   Optional<Voters > findByNin(String nin);

}

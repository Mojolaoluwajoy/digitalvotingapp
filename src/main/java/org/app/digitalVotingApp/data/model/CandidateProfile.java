package org.app.digitalVotingApp.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "candidate_profile")
public class CandidateProfile {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "party_name",nullable = false,unique = true)
    private String partyName;
    @Column(name = "vote_count",nullable = false,unique = true)
    private int voteCount;
    @OneToOne
   @PrimaryKeyJoinColumn(name = "user_id")
    private User user;


    public void incrementVote(){
        voteCount+=getVoteCount();
    }

}
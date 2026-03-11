package org.app.digitalVotingApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "candidates")
public class Candidates {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "candidate_id",nullable = false,unique = true)
    private String candidateId;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "party_name",nullable = false,unique = true)
    private String partyName;
    @Column(name = "nin",nullable = false,unique = true)
    private String nin;
    @Column(name = "vote_count")
    private int voteCount;

    public void incrementVote(){
        voteCount=1+getVoteCount();
    }


}
package org.app.digitalVotingApp.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name  ="votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "vote_id",nullable = false,unique = true)
   private  String voteId;
    @Column(name = "voter_nin",nullable = false,unique = true)
    private String voterNin;
    @Column(name = "candidate_id",nullable = false,unique = true)
    private  String candidateId;

}

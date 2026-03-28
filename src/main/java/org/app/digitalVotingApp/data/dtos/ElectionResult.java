package org.app.digitalVotingApp.data.dtos;

import lombok.Getter;
import lombok.Setter;
import org.app.digitalVotingApp.data.enums.ElectionStatusEnum;
import org.app.digitalVotingApp.data.model.CandidateProfile;

import java.util.List;

@Setter
@Getter
public class ElectionResult {
   private   String ElectionId;
    private  int numberOfVoters;
    private int numberOfCandidates;
    private List<CandidateProfile> candidatesList;
    public CandidateProfile winner;
    private ElectionStatusEnum status;
    private List<CandidateProfile> tiedCandidates;

    public ElectionResult(ElectionStatusEnum status, CandidateProfile winner, List<CandidateProfile> candidatesList, int numberOfVoters, int numberOfCandidates){
        this.status=status;
        this.winner=winner;
        this.candidatesList=candidatesList;
        this.numberOfVoters=numberOfVoters;
        this.numberOfCandidates=numberOfCandidates;
    }
    public ElectionResult(ElectionStatusEnum status, List<CandidateProfile> tiedCandidates, List<CandidateProfile> candidatesList, int numberOfVoters, int numberOfCandidates){
        this.status=status;
        this.tiedCandidates=tiedCandidates;
        this.candidatesList=candidatesList;
        this.numberOfVoters=numberOfVoters;
        this.numberOfCandidates=numberOfCandidates;
    }


    public ElectionResult(ElectionStatusEnum status,List<CandidateProfile> tiedCandidates){
        this.status=status;
        this.tiedCandidates=tiedCandidates;
    }
}

package org.app.digitalVotingApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.exceptions.AlreadyVotedException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/castVote")
    public ResponseEntity<GenericResponse> castVote(@RequestBody VoteRequest voteRequest) throws  VoterNotFoundException,CandidateNotFoundException,AlreadyVotedException{
        VoteRespose voteRespose= voteService.castVote(voteRequest);
return new ResponseEntity<>(GenericResponse.success(voteRespose,"Vote taken successfully"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public  ResponseEntity<GenericResponse> getAllVotes(){
        List <VoteRespose> vote=voteService.getAll();
        return  new ResponseEntity<>(GenericResponse.success(vote,"Votes found"),HttpStatus.ACCEPTED);
    }

}

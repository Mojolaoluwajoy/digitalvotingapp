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
    public ResponseEntity<GenericResponse> castVote(@RequestBody VoteRequest voteRequest) {
        VoteRespose voteRespose;
      try {
          voteRespose = voteService.castVote(voteRequest);
      }catch (VoterNotFoundException e){
         log.error(e.getMessage());
          return new ResponseEntity<>(GenericResponse.failed(e.getMessage()),HttpStatus.BAD_REQUEST);
      }catch (CandidateNotFoundException exception){
          return new ResponseEntity<>(GenericResponse.failed(exception.getMessage()),HttpStatus.BAD_REQUEST);
      }catch (AlreadyVotedException exception){
          return new ResponseEntity<>(GenericResponse.failed(exception.getMessage()),HttpStatus.BAD_REQUEST);
      }
return new ResponseEntity<>(GenericResponse.success(voteRespose,"Vote taken successfully"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public  ResponseEntity<GenericResponse> getAllVotes(){
        List <VoteRespose> vote=voteService.getAll();
        return  new ResponseEntity<>(GenericResponse.success(vote,"Votes found"),HttpStatus.ACCEPTED);
    }

}

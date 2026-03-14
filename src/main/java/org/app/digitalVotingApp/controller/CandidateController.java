package org.app.digitalVotingApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.app.digitalVotingApp.exceptions.CandidateAlreadyExistException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.service.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CandidateServiceImpl candidateService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerCandidates(@RequestBody CandidateRegistrationRequest candidate)throws CandidateAlreadyExistException{
        CandidatesResponse savedVoters=candidateService.register(candidate);
        return new ResponseEntity<>(GenericResponse.success(savedVoters,"Candidate successfully registered"),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllCandidates(){
        List<CandidatesResponse> candidate=candidateService.getAll();
        return new ResponseEntity<>(GenericResponse.success(candidate,"All Candidates"),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> findById(@PathVariable String id)throws CandidateNotFoundException{
        CandidatesResponse savedCandidate=candidateService.getCandidateById(id);
        return new ResponseEntity<>(GenericResponse.success(savedCandidate,"Candidate Found"),HttpStatus.FOUND);

    }
}

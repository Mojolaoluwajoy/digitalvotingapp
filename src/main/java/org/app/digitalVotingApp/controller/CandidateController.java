package org.app.digitalVotingApp.controller;

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

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CandidateServiceImpl candidateService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerCandidates(@RequestBody CandidateRegistrationRequest candidate){
        CandidatesResponse savedVoters=null;
        try{
            savedVoters=candidateService.register(candidate);
        }catch(CandidateAlreadyExistException exception){
            System.out.println(exception.getMessage());
            GenericResponse genericResponse=GenericResponse.failed(exception.getMessage());
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GenericResponse.success(savedVoters,"Candidate successfully registered"),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllCandidates(){
        List<CandidatesResponse> candidate=candidateService.getAll();
        return new ResponseEntity<>(GenericResponse.success(candidate,"All Candidates"),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> findById(@PathVariable String id){
        CandidatesResponse savedCandidate=null;
        try {

            savedCandidate = candidateService.getCandidateById(id);
         }catch (CandidateNotFoundException exception){
            System.out.println(exception.getMessage());

          return new ResponseEntity<>(GenericResponse.failed(exception.getMessage()) ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(GenericResponse.success(savedCandidate,"Candidate Found"),HttpStatus.FOUND);

    }
}

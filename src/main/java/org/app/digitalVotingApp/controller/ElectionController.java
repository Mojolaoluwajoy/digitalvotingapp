package org.app.digitalVotingApp.controller;

import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.app.digitalVotingApp.exceptions.EmptyCandidateListException;
import org.app.digitalVotingApp.data.dtos.ElectionResult;
import org.app.digitalVotingApp.service.ElectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/election")
public class ElectionController {
    @Autowired
    private ElectionResultService electionResultService;

    @GetMapping("/result")
    public ResponseEntity<GenericResponse> getElectionResult()throws EmptyCandidateListException{
        ElectionResult result = electionResultService.getResult();
        return new ResponseEntity<>(GenericResponse.success(result,"Election result fetched successfully"), HttpStatus.FOUND);
    }
}

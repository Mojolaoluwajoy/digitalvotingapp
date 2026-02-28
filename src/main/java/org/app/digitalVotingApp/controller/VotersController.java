package org.app.digitalVotingApp.controller;

import org.app.digitalVotingApp.data.dtos.requests.VotersRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.app.digitalVotingApp.data.dtos.responses.VotersResponses;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.service.VotersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voters")
public class VotersController {

    @Autowired
    VotersServiceImpl votersService;

    @PostMapping("/registerVoters")
    public ResponseEntity<GenericResponse> registerVoters(@RequestBody VotersRegistrationRequest voters) {
        VotersResponses savedVoters = null;
        try {
            savedVoters = votersService.register(voters);
        } catch (VoterAlreadyExistException e) {
            System.out.println(e.getMessage());
            GenericResponse response = GenericResponse.failed(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(GenericResponse.success(savedVoters, "Registration successful"), HttpStatus.CREATED);


    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllVoters(){
        List<VotersResponses> voters=votersService.getAll();
        return new ResponseEntity<>(GenericResponse.success(voters,"Voters found"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <GenericResponse> findVoterById(@PathVariable String id){
        GenericResponse genericResponse;
        try {
            VotersResponses votersResponses = votersService.findById(id);
            genericResponse=GenericResponse.success(votersResponses,"Voter found");
        }catch (VoterNotFoundException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(GenericResponse.failed(exception.getMessage()),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genericResponse,HttpStatus.FOUND);
    }
}

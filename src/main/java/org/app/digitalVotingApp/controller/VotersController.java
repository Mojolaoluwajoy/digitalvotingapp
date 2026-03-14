package org.app.digitalVotingApp.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/voters")
public class VotersController {

  private final   VotersServiceImpl votersService;

  public VotersController(VotersServiceImpl votersService){
      this.votersService=votersService;
  }

    @PostMapping("/registerVoters")
    public ResponseEntity<GenericResponse> registerVoters(@RequestBody VotersRegistrationRequest voters) throws VoterAlreadyExistException {
      VotersResponses savedVoters = votersService.register(voters);
        return new ResponseEntity<>(GenericResponse.success(savedVoters, "Registration successful"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllVoters(){
        List<VotersResponses> voters=votersService.getAll();
        return new ResponseEntity<>(GenericResponse.success(voters,"Voters found"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <GenericResponse> findVoterById(@PathVariable String id)throws VoterNotFoundException{
        VotersResponses votersResponses = votersService.findById(id);
           return new ResponseEntity<>(GenericResponse.success(votersResponses,"Voter found"),HttpStatus.FOUND );
           }
}

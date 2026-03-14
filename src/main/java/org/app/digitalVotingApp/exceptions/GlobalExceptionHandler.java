package org.app.digitalVotingApp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VoterAlreadyExistException.class)
    public ResponseEntity<GenericResponse> handleVoterAlreadyExistException(VoterAlreadyExistException ex) {
        log.error("Error registering new voter: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


@ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<GenericResponse> handleAlreadyVotedException(AlreadyVotedException ex) {
        log.error("Voter already exist: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CandidateAlreadyExistException.class)
    public ResponseEntity<GenericResponse> handleCandidateAlreadyExistException(CandidateAlreadyExistException ex) {
        log.error("Candidate already exist: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<GenericResponse> handleCandidateNotFoundException(CandidateNotFoundException ex) {
        log.error("Candidate not found: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyCandidateListException.class)
    public ResponseEntity<GenericResponse> handleEmptyCandidateListException(EmptyCandidateListException ex) {
        log.error("Empty candidate list: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


@ExceptionHandler(VoterNotFoundException.class)
    public ResponseEntity<GenericResponse> handleVoterNotFoundException(VoterNotFoundException ex) {
    ex.printStackTrace();
        log.error("Voter not found: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleException(Exception ex) {
        ex.printStackTrace();
        log.error("Error processing request: {}", ex.getMessage());
        return new ResponseEntity<>(GenericResponse.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

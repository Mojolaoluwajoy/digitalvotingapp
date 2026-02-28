package org.app.digitalVotingApp.exceptions;

public class CandidateAlreadyExistException extends Exception {
    public CandidateAlreadyExistException(String message) {
        super(message);
    }
}

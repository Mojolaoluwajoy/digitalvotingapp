package org.app.digitalVotingApp.exceptions;

public class VoterAlreadyExistException extends Exception {
    public VoterAlreadyExistException(String message) {
        super(message);
    }
}

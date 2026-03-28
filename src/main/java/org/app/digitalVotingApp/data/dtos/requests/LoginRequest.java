package org.app.digitalVotingApp.data.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LoginRequest {
private String email;
private String password;
}

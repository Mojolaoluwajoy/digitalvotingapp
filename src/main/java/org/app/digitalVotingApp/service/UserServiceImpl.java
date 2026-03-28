package org.app.digitalVotingApp.service;

import lombok.AllArgsConstructor;
import org.app.digitalVotingApp.data.dtos.requests.UserRequest;
import org.app.digitalVotingApp.data.dtos.responses.UserResponse;
import org.app.digitalVotingApp.data.model.User;
import org.app.digitalVotingApp.data.repository.UserRepository;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.app.digitalVotingApp.utils.UserMap.map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse register(UserRequest userRequest)
            throws VoterAlreadyExistException {
        String nin = userRequest.getNin();
        if ( userRepository.findByNin(nin).isPresent())
            throw  new VoterAlreadyExistException ("This user already exists" );

        User user= map(userRequest);

        String encodedPassword=passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(encodedPassword);

        User savedUser =userRepository.save(user);
        return map(savedUser);
    }


    @Override
    public UserResponse findById(String userId)throws VoterNotFoundException {
        User user=userRepository.findByUserId(userId)
                .orElseThrow(()-> new VoterNotFoundException("There's no user registered to this Nin"));

        return map(user);
    }


    @Override
    public List<UserResponse> getAll() {
        List<User> users =userRepository.findAll();
        List <UserResponse> userList=new ArrayList<>();
        for (User savedUser : users){
           UserResponse userResponse =new UserResponse();
            userResponse.setUserId(savedUser.getUserId());
           userResponse.setFirstName(savedUser.getFirstName());
            userResponse.setLastName(savedUser.getLastName());
            userList.add(userResponse);

        }
        return userList;
    }


}


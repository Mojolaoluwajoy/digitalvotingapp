package org.app.digitalVotingApp.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.model.User;
import org.app.digitalVotingApp.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
   private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <User> user=userRepository.findByEmail(username);
       if (user.isEmpty()){
           throw new UsernameNotFoundException(username);
       }
      log.info("User found user={}",user);
      return  UserProfile.parse(user.get());
    }
}

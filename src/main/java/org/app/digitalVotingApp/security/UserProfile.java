package org.app.digitalVotingApp.security;

import jakarta.persistence.Column;
import lombok.Getter;
import org.app.digitalVotingApp.data.enums.Role;
import org.app.digitalVotingApp.data.enums.Status;
import org.app.digitalVotingApp.data.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Getter
public class UserProfile implements UserDetails {
      private String firstName;
      private String lastName;
     private String nin;
     private String password;
      private String email;
      private Role role;
     private LocalDateTime createdAt;
   private Status status;
    @Override
    public boolean isAccountNonExpired() {
        return true; }

    @Override
    public boolean isAccountNonLocked() {
        return true;  }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; }

    @Override
    public boolean isEnabled() {
        return status==Status.ACTIVE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    public static UserProfile parse(User user){
        UserProfile userProfile=new UserProfile();

        userProfile.email= user.getEmail();
        userProfile.firstName= user.getFirstName();
        userProfile.lastName= user.getLastName();
        userProfile.nin=user.getNin();
        userProfile.password=user.getPassword();
        userProfile.createdAt=user.getCreatedAt();
        userProfile.role=user.getRole();
        userProfile.status=user.getStatus();
        return userProfile;
    }
}

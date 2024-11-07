package com.devland.assignment.assignment12.applicationuser;

import com.devland.assignment.assignment12.applicationuser.exception.UserAlreadyExistException;
import com.devland.assignment.assignment12.applicationuser.exception.UserNotFoundException;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.authentication.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = this.applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found."));

        return UserPrincipal.build(applicationUser);
    }

    public void save(ApplicationUser newUser) throws UserAlreadyExistException {
        if (this.applicationUserRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Username '" + newUser.getUsername() + "' Already Exist.");
        }

        if (this.applicationUserRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Email '" + newUser.getEmail() + "' Already Exist.");
        }

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        applicationUserRepository.save(newUser);
    }

    public ApplicationUser getApplicationUser(String username) {
        return this.applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public ApplicationUser findBy(Long userId) {
        return this.applicationUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with Id " + userId + " not found."));
    }
}
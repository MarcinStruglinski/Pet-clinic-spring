package pl.sda.poznan.spring.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserNotFoundException;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.repository.ApplicationUserRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) {

        ApplicationUser applicationUser = applicationUserRepository
                .findByEmail(email)
                .orElseThrow(ApplicationUserNotFoundException::new);
        //todo: Create roles entity and map user roles from db
        GrantedAuthority ga = new SimpleGrantedAuthority("USER");
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Arrays.asList(ga));
    }
}

package pl.sda.poznan.spring.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.repository.ApplicationUserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(ApplicationUser applicationUser) {
        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        this.applicationUserRepository.save(applicationUser);
    }
}

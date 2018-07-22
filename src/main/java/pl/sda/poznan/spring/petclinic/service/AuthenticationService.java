package pl.sda.poznan.spring.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.dto.ApplicationUserDto;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserNotFoundException;
import pl.sda.poznan.spring.petclinic.exception.EmailAlreadyRegisteredException;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.repository.ApplicationUserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ApplicationUserRepository applicationUserRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(ApplicationUser applicationUser) {
        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);

        if (applicationUserRepository.existsByEmail(applicationUser.getEmail()))
            throw new EmailAlreadyRegisteredException();

        this.applicationUserRepository.save(applicationUser);
    }

    public ApplicationUserDto getUserData(String email) {
        return applicationUserRepository
                .findByEmail(email)
                .map(user -> conversionService.convert(user, ApplicationUserDto.class))
                .orElseThrow(ApplicationUserNotFoundException::new);
    }
}

package pl.sda.poznan.spring.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.dto.ApplicationUserDto;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserNotFoundException;
import pl.sda.poznan.spring.petclinic.exception.EmailAlreadyRegisteredException;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserIsActiveException;
import pl.sda.poznan.spring.petclinic.exception.RegisterFailureException;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.repository.ApplicationUserRepository;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final ApplicationUserRepository applicationUserRepository;
  private final ConversionService conversionService;
  private final PasswordEncoder passwordEncoder;

    public void saveUser(ApplicationUser applicationUser) {
        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        applicationUser.setActivationHash(generateMd5HashCode(applicationUser.getEmail()));
        if (applicationUserRepository.existsByEmail(applicationUser.getEmail()))
            throw new EmailAlreadyRegisteredException();

        this.applicationUserRepository.save(applicationUser);
    }

  private String generateMd5HashCode(String md5) {
    try {
      MessageDigest md = java.security.MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      return DatatypeConverter.printHexBinary(array).toLowerCase();
    } catch (NoSuchAlgorithmException e) {
      throw new RegisterFailureException();
    }
  }

  public ApplicationUserDto getUserData(String email) {
    return applicationUserRepository
        .findByEmail(email)
        .map(user -> conversionService.convert(user, ApplicationUserDto.class))
        .orElseThrow(ApplicationUserNotFoundException::new);
  }

  public void activateUserDataByToken(String token) {
    ApplicationUser applicationUser =
        applicationUserRepository
            .findByactivationHash(token)
            .orElseThrow(ApplicationUserNotFoundException::new);
    if (applicationUser.isActivated()) {
      throw new ApplicationUserIsActiveException();
    }
    applicationUser.setActivated(true);
    applicationUserRepository.save(applicationUser);
  }
}

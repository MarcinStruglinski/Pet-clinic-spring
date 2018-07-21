package pl.sda.poznan.spring.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.dto.ApplicationUserDto;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserIsActiveException;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserNotFoundException;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.repository.ApplicationUserRepository;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor









public class AuthenticationService {





    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;



    public void saveUser(ApplicationUser applicationUser) throws UnsupportedEncodingException {
        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        applicationUser.setActivationHash(GenerateMd5HashCode(applicationUser.getEmail()));
        this.applicationUserRepository.save(applicationUser);

        }


    public String GenerateMd5HashCode(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


    public ApplicationUserDto getUserData(String email) {
        return applicationUserRepository
                .findByEmail(email)
                .map(user -> conversionService.convert(user, ApplicationUserDto.class))
                .orElseThrow(ApplicationUserNotFoundException::new);
    }


    public ApplicationUser ActivateUserDataByToken(String token) {
        ApplicationUser applicationUser = applicationUserRepository.findByactivationHash(token).
                orElseThrow(ApplicationUserNotFoundException::new);
        if (applicationUser.isActivated()  )
            throw new ApplicationUserIsActiveException();
            else
            applicationUser.setActivated(true);
            return applicationUser;
    }


}

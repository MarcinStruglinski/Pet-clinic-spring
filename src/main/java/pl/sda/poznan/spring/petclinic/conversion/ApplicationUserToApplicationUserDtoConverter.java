package pl.sda.poznan.spring.petclinic.conversion;

import org.springframework.core.convert.converter.Converter;
import pl.sda.poznan.spring.petclinic.dto.ApplicationUserDto;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;

public class ApplicationUserToApplicationUserDtoConverter implements Converter<ApplicationUser, ApplicationUserDto> {

    @Override
    public ApplicationUserDto convert(ApplicationUser applicationUser) {
        ApplicationUserDto applicationUserDto = new ApplicationUserDto();
        applicationUserDto.setId(applicationUser.getId());
        applicationUserDto.setFirstName(applicationUser.getFirstName());
        applicationUserDto.setLastName(applicationUser.getLastName());
        applicationUserDto.setEmail(applicationUser.getEmail());

        return applicationUserDto;
    }
}

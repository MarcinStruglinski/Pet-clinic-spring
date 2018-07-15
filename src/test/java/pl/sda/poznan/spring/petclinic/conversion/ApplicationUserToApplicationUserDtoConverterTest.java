package pl.sda.poznan.spring.petclinic.conversion;

import org.junit.Before;
import org.junit.Test;
import pl.sda.poznan.spring.petclinic.dto.ApplicationUserDto;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;

import static org.junit.Assert.assertEquals;

public class ApplicationUserToApplicationUserDtoConverterTest {

    ApplicationUser applicationUser;

    @Before
    public void init() {
        this.applicationUser = new ApplicationUser();
        applicationUser.setId(5L);
        applicationUser.setEmail("email@email.com");
        applicationUser.setFirstName("testfirstname");
        applicationUser.setLastName("testlastname");
    }

    @Test
    public void shouldHaveSameIdAfterConversion() {
        //When
        ApplicationUserDto applicationUserDto = new ApplicationUserToApplicationUserDtoConverter().convert(applicationUser);

        //Then
        assertEquals("Converted user has invalid id", applicationUser.getId(), applicationUserDto.getId());
    }

    @Test
    public void shouldHaveSameEmailAfterConversion() {
        //When
        ApplicationUserDto applicationUserDto = new ApplicationUserToApplicationUserDtoConverter().convert(applicationUser);

        //Then
        assertEquals("Converted user has invalid email", applicationUser.getEmail(), applicationUserDto.getEmail());
    }

    @Test
    public void shouldHaveSameFirstnameAfterConversion() {
        //When
        ApplicationUserDto applicationUserDto = new ApplicationUserToApplicationUserDtoConverter().convert(applicationUser);

        //Then
        assertEquals("Converted user has invalid firstname", applicationUser.getFirstName(), applicationUserDto.getFirstName());
    }

    @Test
    public void shouldHaveSameLastnameAfterConversion() {
        //When
        ApplicationUserDto applicationUserDto = new ApplicationUserToApplicationUserDtoConverter().convert(applicationUser);

        //Then
        assertEquals("Converted user has invalid lastname", applicationUser.getLastName(), applicationUserDto.getLastName());
    }
}

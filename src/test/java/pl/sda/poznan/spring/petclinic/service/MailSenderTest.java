package pl.sda.poznan.spring.petclinic.service;

import org.junit.Test;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.model.Email;

public class MailSenderTest {

    @Test
    public void shouldSendEmailWhenCallSendEmailMethod() {
        //Given
        ApplicationUser user = new ApplicationUser();
        user.setEmail("java6poz@gmail.com");
        Email email = new Email();
        email.setSubject("Test email subject");
        email.setMessage("Test email content message");

        //When
        new MailSender().sendEmail(email, user);

        //Then
        //Check user email box!
    }
}

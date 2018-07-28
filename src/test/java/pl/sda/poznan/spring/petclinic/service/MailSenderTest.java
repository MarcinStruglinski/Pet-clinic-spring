package pl.sda.poznan.spring.petclinic.service;

import org.junit.Ignore;
import org.junit.Test;

public class MailSenderTest {

  @Test
  @Ignore
  public void shouldSendEmailWhenCallSendEmailMethod() {
    // Given
    String test_email_subject = "Test email subject";
    String test_email_content_message = "Test email content message";
    String sendTo = "java6poz@gmail.com";

    // When
    new MailSender().sendEmail(test_email_subject, test_email_content_message, sendTo);

    // Then
    // Check user email box!
  }
}

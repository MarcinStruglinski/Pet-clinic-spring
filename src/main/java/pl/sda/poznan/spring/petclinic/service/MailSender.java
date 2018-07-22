package pl.sda.poznan.spring.petclinic.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.exception.RegisterFailureException;

@Service
public class MailSender {

  void sendEmail(String subject, String messageText, String sendTo) {

    final String username = "java6poz@gmail.com";
    final String password = "Javapoz6";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Authenticator authenticator =
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        };
    Session session = Session.getInstance(props, authenticator);

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("java6poz@gmail.com"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
      message.setSubject(subject);
      message.setText(messageText);
      Transport.send(message);
    } catch (MessagingException e) {
      throw new RegisterFailureException();
    }
  }
}

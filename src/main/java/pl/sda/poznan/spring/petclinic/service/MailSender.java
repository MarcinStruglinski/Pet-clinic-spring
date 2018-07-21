package pl.sda.poznan.spring.petclinic.service;

import pl.sda.poznan.spring.petclinic.model.ApplicationUser;
import pl.sda.poznan.spring.petclinic.model.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSender {

    void sendEmail(Email email, ApplicationUser user) {

        final String username = "java6poz@gmail.com";
        final String password = "Javapoz6";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("java6poz@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


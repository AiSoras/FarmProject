/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import objects.User;

/**
 *
 * @author OlesiaPC
 */
public class EMailSender {

    public static synchronized boolean send(User user, String token) {

        String to = user.geteMail();
        String from = "specialforoop@gmail.com";
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("specialforoop", "passwordspecial");
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Password reset");
            message.setText("Dear, " + user.getFirstName() + " " + user.getSecondName() + "!\nThis is your reset password token: " + token + ".\n");

            Transport.send(message);

            return true;
        } catch (MessagingException ignore) {
            return false;
        }

    }
}

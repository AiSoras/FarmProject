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
import org.apache.logging.log4j.LogManager;

/**
 * Sends reset password eMail to user's eMail.
 * @author OlesiaPC
 */
public class EMailSender {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EMailSender.class.getName());

    /**
     * Sends eMail with <code>token</code> to <code>user</code>.
     *
     * @param user User which request reset password.
     * @param token The string contains user's token.
     * @return <code>true</code> if the message is sent successfully, otherwise
     * <code>false</code>.
     */
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
            logger.info("The message was sent successfully");
            return true;
        } catch (MessagingException ignore) {
            logger.error("Exception: ", ignore);
            return false;
        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaEmail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DAVID
 */
public class JavaEmailSender {

    private String emailAddressTo = new String();
    private String msgSubject = new String();
    private String msgText = new String();

    final String USER_NAME = "golber4321@gmail.com";
    final String PASSWORD = "golbercorreo321";
    final String FROM_ADDRESS = "golber4321@gmail.com";

    public void enviarTotal(String correo, String msgSubject, String msgText, String total) {
        String email = msgText + "<br>Precio total del alquiler: " + total;
        createAndSendEmail(correo, msgSubject, email);
    }

    public void createAndSendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.emailAddressTo = emailAddressTo;
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        sendEmailMessage();
    }

    private void sendEmailMessage() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_ADDRESS)); // Set from address of the email
            message.setContent(msgText, "text/html"); // Set content type of the email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddressTo)); // Set email recipient
            message.setSubject(msgSubject); // Set email message subject
            Transport.send(message); // Send email message
            System.out.println("Sent email successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }
}

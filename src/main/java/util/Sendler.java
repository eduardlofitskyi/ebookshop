package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Eduard on 28.11.2014.
 */
public class Sendler extends Thread{
    private String username = "edosgk@gmail.com";
    private String password = "*****";
    private Properties props;
    private String subject = "EBOOKSHOP - Recover password";
    private String text = "Recovery pass:";
    private String toEmail;


    public Sendler(String textUsername, String textPass, String toEmail) {
        this.text+="\nUsername: "+textUsername;
        this.text+="\nPassword: "+textPass;
        this.toEmail = toEmail;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


    }

    @Override
    public void run() {
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //from
            message.setFrom(new InternetAddress(username));
            //to
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //title
            message.setSubject(subject);
            //text
            message.setText(text);
            //send massage
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

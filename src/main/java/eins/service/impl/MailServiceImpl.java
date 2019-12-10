package eins.service.impl;

import eins.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMailRecPass(String email, String link, double min) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom("orok.java@gmail.com");
            helper.setTo(new InternetAddress(email));
            helper.setSubject("Password recovery");
            helper.setText("Hello dear user.<br>" +
                    "If you didn't request to recover your password, please ignore this message.<br>" +
                    "Otherwise, you can tap <a href=\""+link+"\">here</a> to set a new password."+
                    "Please remember that this link is valid only " + min + " minutes.<br>" +
                    "Thank you for using our service!", true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendNewOrder(String email, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom("orok.java@gmail.com");
            helper.setTo(new InternetAddress(email));
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

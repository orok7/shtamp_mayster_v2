package eins.service;

public interface MailService {
    void sendMailRecPass(String email, String link, double min);
    void sendNewOrder(String email, String subject, String text);

}

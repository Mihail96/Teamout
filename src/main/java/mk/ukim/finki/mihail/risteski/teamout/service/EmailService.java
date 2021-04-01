package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService
{
    private final JavaMailSender _javaMailSender;

    public EmailService(JavaMailSender javaMailSender)
    {
        _javaMailSender = javaMailSender;
    }

    public void SendSimpleMessage(String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tteamout@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        _javaMailSender.send(message);
    }
}

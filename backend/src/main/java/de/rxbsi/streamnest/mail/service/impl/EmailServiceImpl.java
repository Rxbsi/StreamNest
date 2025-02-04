package de.rxbsi.streamnest.mail.service.impl;

import de.rxbsi.streamnest.config.RabbitMQConfig;
import de.rxbsi.streamnest.mail.resource.EmailRequest;
import de.rxbsi.streamnest.mail.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailRequest(String to, String subject, String body) {
        EmailRequest emailRequest = new EmailRequest(to, subject, body);
        amqpTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.EMAIL_ROUTING_KEY, emailRequest);
        System.out.println("📩 E-Mail-Request sent to RabbitMQ: " + emailRequest);

    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receiveMessage(EmailRequest emailRequest) {
        System.out.println("📨 E-Mail-Request received: " + emailRequest);
        sendEmail(emailRequest);
    }

    @Override
    public void sendEmail(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody(), true);

            mailSender.send(message);
            System.out.println("E-Mail sent to " + emailRequest.getTo());
        } catch (MessagingException e) {
            System.err.println("Error while sending mail: " + e.getMessage());
        }
    }
}

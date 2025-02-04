package de.rxbsi.streamnest.mail.service;

import de.rxbsi.streamnest.mail.resource.EmailRequest;

public interface EmailService {

    /**
     * Sends an Email to RabbitMQ
     *
     * @param to        to email
     * @param subject   email subject
     * @param body      email content
     */
    void sendEmailRequest(String to, String subject, String body);

    /**
     * Sends the email to the user
     *
     * @param emailRequest The Email Request
     */
    void sendEmail(EmailRequest emailRequest);

}

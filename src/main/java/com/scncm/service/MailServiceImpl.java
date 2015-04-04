package com.scncm.service;


import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final String sendGridUsername = "bogdan";
    private static final String sendGridPassword = "passpass";
    private static final String sendGridFrom     = "no-reply@gmail.com";

    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public Boolean sendMessage(String toEmail, String subject, String htmlContent) {
        SendGrid sendGridClient = new SendGrid(sendGridUsername, sendGridPassword);
        SendGrid.Email message = new SendGrid.Email();
        message.addTo(toEmail);
        message.setFrom(sendGridFrom);
        message.setSubject(subject);
        message.setHtml(htmlContent);

        try {
            SendGrid.Response response = sendGridClient.send(message);
            boolean success = response.getStatus();

            if(success) {
                logger.info("Email sent successfully.");
            } else {
                logger.warn("Email was not sent. Response from SendGrid: code=" + response.getCode() + ", " +
                        "message=" + response.getMessage());
            }

            return success;
        } catch (SendGridException e) {
            logger.warn("Error occurred while trying to send email. ", e);
            return false;
        }
    }
}

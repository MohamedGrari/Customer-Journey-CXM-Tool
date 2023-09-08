package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Bike;
import com.mitacs.customerjourney.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailSenderC {

    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender javaMailSender;

    public MailSenderC(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Customer customer, List<Bike> bikes, String subject) {
        try {
            // Create an HTML message
            StringBuilder msgBody = new StringBuilder();
            msgBody.append("<html><body>");

            // Add a greeting
            msgBody.append("<h2>Hello ").append(customer.getName()).append(",</h2>");

            // Add a message
            msgBody.append("<p>Here are some recommended bikes:</p>");

            // Create a list of bikes with links to buy each bike
            msgBody.append("<ul>");
            for (Bike bike : bikes) {
                msgBody.append("<li>");
                msgBody.append("<strong>").append(bike.getName()).append("</strong><br>");
                msgBody.append("Price: $").append(bike.getPrice()).append("<br>");
                msgBody.append("Description: ").append(bike.getDescription()).append("<br>");
                msgBody.append("<a href='http://localhost:4200/bikes/").append(bike.getRef()).append("'>Buy Now</a>");
                msgBody.append("</li>");
            }

            // Add a closing message
            msgBody.append("<p>Thank you for choosing our products!</p>");

            // Close HTML
            msgBody.append("</body></html>");

            // Create a MimeMessage with HTML content
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(customer.getEmail());
            helper.setText(msgBody.toString(), true);
            helper.setSubject(subject);

            // Send the email
            javaMailSender.send(mimeMessage);

            System.out.println("Mail sent to: " + customer.getName());
        }
        catch (Exception e) {
            System.out.println("Error while sending email to " + customer.getName() + " : " + e);
        }
    }

}

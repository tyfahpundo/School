package zw.co.afrosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.dto.response.StudentResponse;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */
@Service
public class MailService {
    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendEmail(StudentResponse student) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(student.getContactDetail().getEmail());
        mail.setSubject("Enrollment Email");
        mail.setText("Congratulations you have been added as a Student.....Your Registration process coming soon.....");
        javaMailSender.send(mail);
    }
    public void sendEmailWithAttachment(Student student) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(student.getContactDetail().getEmail());
        helper.setSubject("Enrollment Email");
        helper.setText("Please find the attached document below.");

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(mimeMessage);
    }


}

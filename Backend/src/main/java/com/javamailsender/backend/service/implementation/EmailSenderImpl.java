package com.javamailsender.backend.service.implementation;

import com.javamailsender.backend.service.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);

    @Override
    public void sendMailToOnePerson(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("pratyushsinha934@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);

        logger.info("Successfully send message");

    }

    @Override
    public void sendMailToManyPeople(String[] to, String subject, String message) {
        if (to == null || to.length == 0) {
            logger.info("Please fill the array");
            return;
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("pratyushsinha934@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);

        logger.info("Successfully send to multiple people");
    }

    @Override
    public void sendMailInTheFormOfHTML(String to, String subject, String htmlElement) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("pratyushsinha934@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlElement, true);

            javaMailSender.send(message);
            logger.info("Successfully send in HTML form");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendMailIncludingFile(String to, String subject, String message, File file) {

        MimeMessage message1 = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message1, true);
//            File file1 = new File("/home/pratyush/Desktop/Programming/EmailSender/Backend/src/main/resources/static/B.Tech Second Year Proposal.pdf");
            helper.setFrom("pratyushsinha934@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), file);
            javaMailSender.send(message1);
            logger.info("Successfully send message in the form of files");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMailIncludingFileWithPath(String to, String subject, String message, InputStream inputStream) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("pratyushsinha934@gmail.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);

            File file = new File("src/Pdf.pdf");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);

            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), file);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

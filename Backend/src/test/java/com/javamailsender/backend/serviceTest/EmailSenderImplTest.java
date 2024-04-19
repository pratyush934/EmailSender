package com.javamailsender.backend.serviceTest;

import com.javamailsender.backend.service.implementation.EmailSenderImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
public class EmailSenderImplTest {

    @Autowired
    private EmailSenderImpl emailSender;

    @Test
    void sendMailToOnePerson() {
        System.out.println("Kya Baat hai you are inside sendMailToOnePerson wala method");
        emailSender.sendMailToOnePerson("oggycandle786@gmail.com", "I am Learning email sending from backend", "How are you pratyush");

    }

    @Test
    void sendEmailToManyPeople() {
        System.out.println("Kya Baat hai you are inside sendEmailToManyPeople wala method");
        String[] array = {"oggycandle786@gmail.com", "2022594965.pratyush@ug.sharda.ac.in"};
        emailSender.sendMailToManyPeople(array, "I am learning sending email to multiple people", "You got another success bro");
        System.out.println("Lagta hai chal gaya sasura");
    }

    @Test
    void sendEmailInTheFormOfHtml() {
        System.out.println("Kya baat hai you are inside sendEmailInTheFormOfHtml wala method");
        String htmlContent = "" + "<h1 style='color:red'>Congratulations in achieving another milestone, I am really really happy in seeing you progress</h1>" + "";
        emailSender.sendMailInTheFormOfHTML("oggycandle786@gmail.com", "I am learning sending email in the form of html", htmlContent);
        System.out.println("Ye badhiya se chal raha hai , everything is woriking fine");
    }
    @Test
    void sendEmailIncludingFile() {
        System.out.println("I am really Happy as we are inside sendEmailIncludingFile wala method");
        File newFIle = new File("/home/pratyush/Desktop/Programming/EmailSender/Backend/src/main/resources/static/B.Tech Second Year Proposal.pdf");
        emailSender.sendMailIncludingFile("oggycandle786@gmail.com", "I am Learning how to send things through email", "I am very Happy", newFIle);
        System.out.println("Bahut khub bacche you are doing great job");
    }

    @Test
    void sendEmailIncludingFileWithPath() {
        String url = "/home/pratyush/Desktop/Programming/EmailSender/Backend/src/main/resources/static/B.Tech Second Year Proposal.pdf";
        System.out.println("I am really happy with the progress");
        try {
            emailSender.sendMailIncludingFileWithPath("oggycandle786@gmail.com", "I am learning how to send email", "Good Going bro", new FileInputStream(url));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Kya baat hai tune to kar dikhaya");
    }
}

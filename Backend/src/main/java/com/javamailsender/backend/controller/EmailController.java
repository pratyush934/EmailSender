package com.javamailsender.backend.controller;

import com.javamailsender.backend.model.Message;
import com.javamailsender.backend.service.implementation.EmailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {


    @Autowired
    private EmailSenderImpl emailSender;

    @PostMapping("/send-one-person")
    public ResponseEntity<?> sendEmailWithText(@RequestBody Message message) {

        emailSender.sendMailToOnePerson(message.getTo(), message.getSubject(), message.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/send-many-people")
    public ResponseEntity<?> sendEmailWithTextToManyPeople(@RequestBody Message message) {
        emailSender.sendMailToManyPeople(message.getArray(), message.getSubject(), message.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/send-with-html")
    public ResponseEntity<?> sendEmailWithHTML(@RequestBody Message message) {
        emailSender.sendMailInTheFormOfHTML(message.getTo(), message.getSubject(), message.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<?> sendEmailWithFileObject(@RequestPart Message message, @RequestPart("file") MultipartFile file) {
        try {
            String directory = "/home/pratyush/Desktop/Programming/EmailSender/Backend/src/main/resources/static/";
            File convFile = new File(directory + File.separator  + Objects.requireNonNull(file.getOriginalFilename()));
            file.transferTo(convFile);
            emailSender.sendMailIncludingFile(message.getTo(), message.getSubject(), message.getMessage(), convFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/send-with-file-path")
    public ResponseEntity<?> sendEmailWithFile(@RequestPart Message message, @RequestPart MultipartFile file) throws IOException {
        emailSender.sendMailIncludingFileWithPath(message.getTo(), message.getSubject(), message.getMessage(), file.getInputStream());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

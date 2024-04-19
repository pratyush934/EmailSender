package com.javamailsender.backend.service;

import java.io.File;
import java.io.InputStream;

public interface EmailSender {

//    to one person
    void sendMailToOnePerson(String to, String subject, String message);

//    to many person
    void sendMailToManyPeople(String[] to, String subject, String message);

//    to one person but in the form of html
    void sendMailInTheFormOfHTML(String to, String subject, String htmlElement);

//    to one person but also with file
    void sendMailIncludingFile(String to, String subject, String message, File file);

//    to one person directly with the filePath
    void sendMailIncludingFileWithPath(String to, String subject, String message, InputStream inputStream);
}

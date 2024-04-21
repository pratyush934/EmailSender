package com.javamailsender.backend.model;


import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Message {

    private String to;
    private String[] array = {};
    private String subject;
    private String message;

}

package io.ibex.bookservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Library {

    @GetMapping (value = "/v1/message")
    public void printMessage() {
        log.info("Print message...");
    }
}

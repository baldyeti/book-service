package io.ibex.bookservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/book/v1")
@RestController
public class BookController {

    @GetMapping(value = "/books")
    public void books() {
        log.info("Books");
    }
}

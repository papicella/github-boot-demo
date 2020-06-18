package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class FrontEnd {
    @GetMapping("/")
    public String index () {
        log.info("An INFO Message for this GitHub Action Demo");

        return new Date().toString();
    }
}

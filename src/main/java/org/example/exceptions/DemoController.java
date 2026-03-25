package org.example.exceptions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo/divide-by-zero")
    public String divideByZero() {
        int result = 10 / 0;
        return "Result: " + result;
    }

    @GetMapping("/demo/unexpected-error")
    public String unexpectedError() {
        throw new IllegalStateException("Unexpected demo exception");
    }
}


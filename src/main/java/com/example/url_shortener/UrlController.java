package com.example.url_shortener;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    private Map<String, String> urlStore = new HashMap<>();

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String originalUrl) {
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        urlStore.put(shortCode, originalUrl);
        return "Short URL: http://localhost:8080/api/" + shortCode;
    }

    @GetMapping("/{code}")
    public String redirect(@PathVariable String code) {
        String original = urlStore.get(code);
        if (original == null) {
            return "URL not found!";
        }
        return "Redirecting to: " + original;
    }
}

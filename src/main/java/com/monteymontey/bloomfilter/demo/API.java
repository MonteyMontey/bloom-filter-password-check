package com.monteymontey.bloomfilter.demo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@SuppressWarnings("ALL")
@RestController
public class API {
    BloomFilter<String> bloomFilter;

    @SuppressWarnings("UnstableApiUsage")
    public API() throws IOException {
        try (InputStream inputStream = new FileInputStream("src/main/resources/static/bloom-filter.txt")) {
            this.bloomFilter = BloomFilter.readFrom(inputStream, Funnels.stringFunnel(Charset.defaultCharset()));
        }
    }

    @GetMapping(value = "/password-leaked", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Response> passwordLeaked(@RequestParam String sha1Password) {
        return ResponseEntity.ok(new Response(bloomFilter.mightContain(sha1Password.toUpperCase())));
    }
}


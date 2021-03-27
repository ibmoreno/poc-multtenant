package com.idcotton.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppController implements Serializable {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping({"/status", "/check"})
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping(value = "/versao")
    public ResponseEntity<String> versao() {
        return ResponseEntity.ok(appVersion);
    }

}

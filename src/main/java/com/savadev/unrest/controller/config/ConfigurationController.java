package com.savadev.unrest.controller.config;

import com.savadev.unrest.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

    private final ConfigService configService;

    @Autowired
    public ConfigurationController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping
    Mono<ResponseEntity<ConfigResponse>> getConfig() {
        return configService.getConfiguration().map(config -> ResponseEntity.ok(new ConfigResponse(config)));
    }
@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/shutdown")
    public Mono<ResponseEntity<Void>> shutdownServer() {
        return configService.shutdownServer()
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @PostMapping("/sleep")
    public Mono<ResponseEntity<Void>> sleepServer() {
        return configService.sleepServer()
                .then(Mono.just(ResponseEntity.ok().build()));
    }
}
}

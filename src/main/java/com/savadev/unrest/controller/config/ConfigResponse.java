package com.savadev.unrest.controller.config;

import com.savadev.unrest.domain.config.Config;

public class ConfigResponse {

    private final Config config;

    public ConfigResponse(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }
  
    @PostMapping("/shutdown")
    Mono<ResponseEntity<Void>> shutdownServer() {
        return configService.shutdownServer().map(ResponseEntity::ok);
    }

    @PostMapping("/sleep")
    Mono<ResponseEntity<Void>> sleepServer() {
        return configService.sleepServer().map(ResponseEntity::ok);
    }
}

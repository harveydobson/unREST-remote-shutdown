package com.savadev.unrest.service.config;

import com.savadev.unrest.dao.ini.VarRepository;
import com.savadev.unrest.domain.config.Config;
import com.savadev.unrest.domain.config.UnraidConfig;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public class UnraidConfigService implements ConfigService {

    private final VarRepository repository;

    public UnraidConfigService(VarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Config> getConfiguration() {
        return Mono.zip(repository.getVersion(), repository.getTimezone(), repository.getComment())
                .map(objects -> new UnraidConfig(objects.getT1(), objects.getT2(), objects.getT3()));
    }

    @Override
        public Mono<Void> shutdownServer() {
            // Implement the logic to shut down the server
            return Mono.fromRunnable(() -> {
                // Command to shut down the server
                Runtime.getRuntime().exec("shutdown -h now");
            });
        }

        @Override
        public Mono<Void> sleepServer() {
            // Implement the logic to put the server to sleep
            return Mono.fromRunnable(() -> {
                // Command to put the server to sleep
                Runtime.getRuntime().exec("systemctl suspend");
            });
        }

}

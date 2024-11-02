package com.savadev.unrest.service.config;

import com.savadev.unrest.dao.ini.VarRepository;
import com.savadev.unrest.domain.config.Config;
import com.savadev.unrest.domain.config.UnraidConfig;
import org.springframework.stereotype.Service;
import java.io.IOException;
import reactor.core.publisher.Mono;

@Service
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
        return Mono.fromRunnable(() -> {
            try {
                Runtime.getRuntime().exec("shutdown -h now");
            } catch (IOException e) {
                // Handle the exception, e.g., log it
                e.printStackTrace();
            }
        });
    }

    @Override
    public Mono<Void> sleepServer() {
        return Mono.fromRunnable(() -> {
            try {
                Runtime.getRuntime().exec("systemctl suspend");
            } catch (IOException e) {
                // Handle the exception
                e.printStackTrace();
            }
        });
    }
}

package com.selftechlearner.doctor_service.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    private final Random random = new Random();

    @Override
    public Optional<String> getCurrentAuditor() {
        String[] list = {"Mahfooz", "Mawiya", "Nazreen", "Aamra", "Maaz"};
        String createdBy = list[random.nextInt(list.length)];
        return Optional.of(createdBy);
    }
}

package com.core.backend.service;

import com.core.backend.domain.repository.HeadCoachRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HeadCoachService {

    private final HeadCoachRepository headCoachRepository;

    private final PasswordEncoder passwordEncoder;

    public HeadCoachService(HeadCoachRepository headCoachRepository, PasswordEncoder passwordEncoder) {
        this.headCoachRepository = headCoachRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public createHeadCoach()
}

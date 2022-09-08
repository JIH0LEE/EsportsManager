package com.core.backend.service;

import com.core.backend.controller.dto.HeadCoachRequest;
import com.core.backend.controller.dto.HeadCoachResponse;
import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.exception.IsNotSamePassword;
import com.core.backend.exception.IsNotValidHeadCoachName;
import com.core.backend.exception.NoValidHeadCoach;
import com.core.backend.exception.PasswordNotMatch;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HeadCoachService {

    private final HeadCoachRepository headCoachRepository;
    private final PasswordEncoder passwordEncoder;

    private boolean isValidName(String name) {
        HeadCoach headCoach = headCoachRepository.findByName(name).orElse(null);
        if (headCoach == null) {
            return true;
        }
        return false;
    }

    private boolean isSamePassword(String password, String passwordCheck) {
        if (password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }

    public HeadCoachResponse createHeadCoach(HeadCoachRequest headCoachRequest) {
        if (!isValidName(headCoachRequest.getName())) {
            throw new IsNotValidHeadCoachName();
        }
        if (!isSamePassword(headCoachRequest.getPassword(), headCoachRequest.getPasswordCheck())) {
            throw new IsNotSamePassword();
        }
        HeadCoach headCoach = HeadCoach.builder()
            .name(headCoachRequest.getName())
            .password(passwordEncoder.encode(headCoachRequest.getPassword()))
            .build();
        headCoachRepository.save(headCoach);
        return HeadCoachResponse.of(headCoach);
    }

    public HeadCoachResponse login(HeadCoachRequest headCoachRequest) {
        HeadCoach headCoach = headCoachRepository.findByName(headCoachRequest.getName())
            .orElseThrow(NoValidHeadCoach::new);
        if (!passwordEncoder.matches(headCoachRequest.getPassword(), headCoach.getPassword())) {
            throw new PasswordNotMatch();
        }
        return HeadCoachResponse.of(headCoach);
    }

    public MessageResponse deleteHeadCoachById(Long id){
        headCoachRepository.deleteById(id);
        return new MessageResponse(true,"정상적으로 삭제되었습니다.");
    }
}

package com.zrana.springit.config;

import com.zrana.springit.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.of("master@gmail.com");
        }
        return Optional.of(
                ((User)SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getEmail());
    }
}

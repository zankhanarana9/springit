package com.zrana.springit.config;

import com.zrana.springit.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
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

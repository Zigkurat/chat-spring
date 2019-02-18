package com.example.chat.security.service.local;

import com.example.chat.persistence.entity.user.User;
import com.example.chat.persistence.repository.UserRepository;
import com.example.chat.security.model.local.UserPrincipal;
import com.example.chat.service.user.exception.UserNotFoundForEmailException;
import com.example.chat.service.user.exception.UserNotFoundForIdException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public LocalUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundForEmailException(email));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundForIdException(id));

        return UserPrincipal.create(user);
    }
}

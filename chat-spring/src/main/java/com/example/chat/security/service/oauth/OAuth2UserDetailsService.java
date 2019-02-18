package com.example.chat.security.service.oauth;

import com.example.chat.persistence.entity.constant.AuthProvider;
import com.example.chat.persistence.entity.constant.UserRole;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.persistence.repository.UserRepository;
import com.example.chat.security.exception.AlreadySignedUnWithDifferentProviderException;
import com.example.chat.security.exception.ProviderIsMissingEmailException;
import com.example.chat.security.model.local.UserPrincipal;
import com.example.chat.security.model.oauth.OAuth2UserInfo;
import com.example.chat.security.model.oauth.OAuth2UserInfoFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

    private UserRepository userRepository;

    public OAuth2UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException exception) {
            throw exception;
        } catch (Exception exception) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(exception.getMessage(), exception.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new ProviderIsMissingEmailException();
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getProvider().name().equalsIgnoreCase(oAuth2UserRequest.getClientRegistration().getRegistrationId())) {
                throw new AlreadySignedUnWithDifferentProviderException(user.getProvider().name());
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setUsername(oAuth2UserInfo.getName().trim());
        user.setRole(UserRole.USER);
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()));
        user.setAvatarUrl(oAuth2UserInfo.getImageUrl());

        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUsername(oAuth2UserInfo.getName().trim());
        existingUser.setAvatarUrl(oAuth2UserInfo.getImageUrl());

        return userRepository.save(existingUser);
    }
}

package com.zrana.springit.service;

import com.zrana.springit.domain.User;
import com.zrana.springit.repository.RoleRepository;
import com.zrana.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final MailService mailService;

    private RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService, MailService mailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mailService = mailService;
        //To do - pass encoder as bean rather than initializing a new every time
        encoder=new BCryptPasswordEncoder();
    }

    public User register(User user) {
        //take the password from the form and encode it
        String secret = "{bcrypt}"+encoder.encode(user.getPassword());
        user.setPassword(secret);
        //confirm password
        user.setConfirmPassword(secret);
        //assign a role to the user
        user.addRole(roleService.findByName("ROLE_USER"));
        //set an activation code
        user.setActivationCode(UUID.randomUUID().toString());
        //disable the user

        //save user
        save(user);
        //send the activation email
        sendActivationEmail(user);
        //return user
        return user;
    }

    public void sendWelcomeEmail(User user) {
        mailService.sendWelcomeEmail(user);
    }
    public void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void saveUsers(User... users) {
        //begin transaction
        for(User user: users) {
            logger.info("Saving user: " + user.getEmail());
//            try {
//                userRepository.save(user);
//            }
//            catch(Exception e) {
//                //rollback trsancation
//            }
            userRepository.save(user);
            //commit transaction
        }
    }

    public Optional<User> findByEmailAndActivationCode(String email, String activationCode) {
        return userRepository.findByEmailAndActivationCode(email,activationCode);
    }
}

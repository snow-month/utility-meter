package ru.homelab.service.impl;

import ru.homelab.entity.MessageAudit;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.repository.UserRepository;
import ru.homelab.service.AuditService;
import ru.homelab.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuditService auditService;

    public UserServiceImpl(UserRepository userRepository, AuditService auditService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
    }

    @Override
    public User getUserByLogin(String login) throws NoUserException {
        auditService.save(MessageAudit.AUTHORIZATION);
        return userRepository.getUserByLogin(login);
    }

    @Override
    public void save(User user) {
        auditService.save(MessageAudit.CREATE_NEW_USER);
        userRepository.save(user);
    }
}

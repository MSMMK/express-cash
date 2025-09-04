package org.example.expresscash.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.mappers.UserMapper;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.UserModel;
import org.example.expresscash.repositories.UserRepository;
import org.example.expresscash.services.AuthService;
import org.example.expresscash.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserModel> search(SearchCriteria searchCriteria, Pageable pageable) {
        Long userId = authService.authUser().getId();
        return userRepository.search(searchCriteria, userId, pageable, entityManager)
                .getContent()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
    }
}

package org.example.expresscash.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.constants.StatusEnum;
import org.example.expresscash.constants.UserTypeEnum;
import org.example.expresscash.entity.Status;
import org.example.expresscash.entity.User;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.mappers.UserMapper;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.UserModel;
import org.example.expresscash.repositories.StatusRepository;
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
    private final StatusRepository statusRepository;
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

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.USER_NOT_FOUND, "User not Found"));

        userRepository.delete(user);
    }

    @Override
    public void changeStatus(Long userId, StatusEnum statusCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.USER_NOT_FOUND, "User not found"));

        Status status = statusRepository.findByCode(statusCode)
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.INVALID_STATUS, "Status not found"));

        user.setStatus(status);
        userRepository.save(user);
    }

    @Override
    public UserModel getUserById(Long userId) {
        UserModel user = userRepository.findById(userId)
                .map(userMapper::toModel)
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.USER_NOT_FOUND, "User not found"));
        //TODO add user sim numbers
        return null;
    }


}

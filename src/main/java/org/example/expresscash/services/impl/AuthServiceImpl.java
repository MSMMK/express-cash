package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.constants.StatusEnum;
import org.example.expresscash.constants.UserTypeEnum;
import org.example.expresscash.entity.Status;
import org.example.expresscash.entity.User;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.exceptions.TokenExpiredException;
import org.example.expresscash.mappers.LookupsMapper;
import org.example.expresscash.mappers.UserMapper;
import org.example.expresscash.model.AuthRequest;
import org.example.expresscash.model.AuthResponse;
import org.example.expresscash.model.RegisterRequest;
import org.example.expresscash.repositories.BranchRepository;
import org.example.expresscash.repositories.StatusRepository;
import org.example.expresscash.repositories.UserRepository;
import org.example.expresscash.repositories.UserTypeLookupRepository;
import org.example.expresscash.services.AuthService;
import org.example.expresscash.services.CityService;
import org.example.expresscash.services.GovernorateService;
import org.example.expresscash.utils.FileUtil;
import org.example.expresscash.utils.JwtService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtil;
    private final UserMapper userMapper;
    private final GovernorateService governorateLookupService;
    private final CityService cityLookupService;
    private final LookupsMapper lookupsMapper;
    private final UserTypeLookupRepository userTypeLookupRepository;
    private final StatusRepository statusRepository;
    private final BranchRepository branchRepository;

    @Override
    public ResponseEntity<String> register(RegisterRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        User user = userMapper.toEntity(signUpRequest);
        if(!signUpRequest.getUserType().equals(UserTypeEnum.ADMIN)){
            user.setSuperUser(new User(authUser().getId()));
            user.setBranch(branchRepository.findByCode(signUpRequest.getBranchCode()).orElseThrow(() -> new BusinessException(StatusCodeEnum.BRANCH_NOT_FOUND, "branch with code {} not found", signUpRequest.getBranchCode())));
        }
        user.setUserType(userTypeLookupRepository.findByCode(signUpRequest.getUserType()).orElseThrow(() -> new BusinessException(StatusCodeEnum.INVALID_USER_TYPE, "user type not valid")));
        user.setGovernorate(lookupsMapper.toGovEntity(governorateLookupService.findById(signUpRequest.getGovId())));
        user.setCity(lookupsMapper.toCityEntity(cityLookupService.findById(signUpRequest.getCityId())));
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setStatus(statusRepository.findByCode(StatusEnum.ACTIVE).orElse(new Status(1L)));
        if (signUpRequest.getProfileImage() != null) {
            user.setProfileImage(FileUtil.saveFile(user.getUsername(), signUpRequest.getProfileImage()));
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.USER_NOT_FOUND, "User With Username {} Not Found", loginRequest.getUsername()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(user.getUsername());
        AuthResponse response = AuthResponse.builder().token(jwt).details(userMapper.toModel(user)).build();
        return ResponseEntity.ok(response);
    }

    @Override
    public User authUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (User) authentication.getPrincipal();
    }



}


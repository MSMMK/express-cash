package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.entity.Role;
import org.example.expresscash.mappers.RoleMapper;
import org.example.expresscash.model.RoleModel;
import org.example.expresscash.repositories.RoleRepository;
import org.example.expresscash.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    public void addRole(RoleModel roleModel) {
//        Role role = roleMapper.toEntity(roleModel);
//        roleRepository.save(role);
    }
}

package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.RoleModel;
import org.example.expresscash.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleResource {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody RoleModel roleModel) {
        roleService.addRole(roleModel);
        return ResponseEntity.ok("Role Added Successfully");
    }
}

package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.UserModel;
import org.example.expresscash.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("users")
public class UserResource {
    private final UserService userService;

    @PostMapping
    public List<UserModel> listUsers(@RequestBody SearchCriteria searchCriteria, Pageable pageable) {
        return userService.search(searchCriteria, pageable);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }
}

package org.example.expresscash.services;

import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.UserModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserModel> search(SearchCriteria searchCriteria, Pageable pageable);
}

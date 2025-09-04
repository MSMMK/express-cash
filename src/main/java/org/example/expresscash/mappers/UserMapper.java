package org.example.expresscash.mappers;

import org.example.expresscash.entity.User;
import org.example.expresscash.model.RegisterRequest;
import org.example.expresscash.model.UserModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {LookupsMapper.class, BranchMapper.class})
public interface UserMapper {

    User toEntity(RegisterRequest userModel);

    @Mappings({
            @Mapping(target = "userType", source = "userType.code"),
    })
    UserModel toModel(User user);

    @AfterMapping
    default void mapImage(@MappingTarget UserModel model, User user) {
        model.setProfileImage("http://localhost:5050.api" + user.getProfileImage());
    }
}

package com.mycompany.signupservice.mapper;

import com.mycompany.signupservice.dto.UserPhone;
import com.mycompany.signupservice.dto.entity.Phone;
import com.mycompany.signupservice.dto.entity.User;
import com.mycompany.signupservice.dto.request.SignupRequest;
import com.mycompany.signupservice.dto.response.SignupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "active", ignore = true)
    User requestUserToUser(SignupRequest requestUser);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "created", target = "created", dateFormat = "MMM d, yyyy hh:mm:ss a")
    @Mapping(source = "lastLogin", target = "lastLogin", dateFormat = "MMM d, yyyy hh:mm:ss a")
    @Mapping(source = "token", target = "token")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    SignupResponse userToUserResponse(User user);

    List<Phone> mapUserPhonesToPhones(List<UserPhone> userPhones);

    List<UserPhone> mapPhonesToUserPhones(List<Phone> phones);
}

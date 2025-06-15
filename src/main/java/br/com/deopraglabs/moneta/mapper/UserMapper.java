package br.com.deopraglabs.moneta.mapper;

import br.com.deopraglabs.moneta.domain.User;
import br.com.deopraglabs.moneta.dtos.request.CreateUserRequest;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(
        source = "", target = ""
    )
    User parseCreateUserRequest(CreateUserRequest userRequest);


}

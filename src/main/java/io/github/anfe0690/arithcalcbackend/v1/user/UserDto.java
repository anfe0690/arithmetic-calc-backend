package io.github.anfe0690.arithcalcbackend.v1.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    public String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    public String name;

    public UserDto() {
    }

    public UserDto(UserEntity entity) {
        this.username = entity.getUsername();
        this.name = entity.getName();
    }
}

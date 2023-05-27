package io.github.anfe0690.arithcalcbackend.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    public String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    public String name;
}

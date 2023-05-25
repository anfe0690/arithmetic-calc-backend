package io.github.anfe0690.arithcalcbackend.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers(@RequestParam(name = "page", defaultValue = "0") int page) {
        return "Hello there";
    }
}

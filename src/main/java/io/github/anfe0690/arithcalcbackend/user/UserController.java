package io.github.anfe0690.arithcalcbackend.user;

import io.github.anfe0690.arithcalcbackend.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/log-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postLogIn(@RequestBody UserDto user, HttpSession session) {
        logger.info("Logging in \"{}\"", user.username);

        UserDto sessionUser = (UserDto) session.getAttribute("user");
        if (sessionUser != null) {
            logger.info("User already logged in");
            return ResponseEntity.ok(sessionUser);
        }
        else {
            UserEntity userEntity = userRepository.findByUsernameAndPasswordHashAndStatus(user.username,
                    Utils.getPasswordHash(user.password), Status.ACTIVE);
            if (userEntity != null) {
                UserDto newUser = new UserDto(userEntity);
                session.setAttribute("user", newUser);
                logger.info("Logged in");

                return ResponseEntity.ok(newUser);
            }
            else {
                String message = "Incorrect username or password.";
                logger.info(message);
                return ResponseEntity.badRequest().body(message);
            }
        }
    }

    @DeleteMapping("/log-out")
    public ResponseEntity<?> deleteLogOut(HttpSession session) {
        logger.info("Logging out");

        UserDto sessionUser = (UserDto) session.getAttribute("user");
        if (sessionUser != null) {
            String username = sessionUser.username;
            session.invalidate();
            logger.info("Logged out \"{}\"", username);
        }
        else {
            logger.info("There is no active session to delete.");
        }
        return ResponseEntity.noContent().build();
    }
}

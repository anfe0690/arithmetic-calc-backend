package io.github.anfe0690.arithcalcbackend.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/log-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postLogIn(@RequestBody User user, HttpSession session) {
        logger.info("Logging in \"{}\"", user.username);

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            logger.info("User already logged in");
            return ResponseEntity.ok(sessionUser);
        }
        else if (user.username.equals("admin@gmail.com")) {
            user.name = "Peter Parker";
            session.setAttribute("user", user);

            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.badRequest().body("Incorrect username or password.");
        }
    }

    @DeleteMapping("/log-out")
    public ResponseEntity<?> deleteLogOut(HttpSession session) {
        logger.info("Logging out");

        User sessionUser = (User) session.getAttribute("user");
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

package io.github.anfe0690.arithcalcbackend;

import io.github.anfe0690.arithcalcbackend.v1.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        switch (request.getMethod()) {
            case "GET":
            case "POST":
            case "PUT":
            case "DELETE":
                break;
            default:
                return true;
        }

        if (request.getServletPath().equals(UserController.LOG_IN_URL)
                || request.getServletPath().equals(UserController.LOG_OUT_URL)) {
            logger.info("No need to check the session for {}", request.getServletPath());
            return true;
        }
        else if (request.getSession().getAttribute("user") == null) {
            logger.info("There is no session for {}", request.getServletPath());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        else {
            logger.info("There is a session for {}", request.getServletPath());
            return true;
        }
    }
}

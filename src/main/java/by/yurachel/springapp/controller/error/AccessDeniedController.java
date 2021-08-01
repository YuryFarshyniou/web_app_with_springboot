package by.yurachel.springapp.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AccessDeniedController implements ErrorController {

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "error/access-denied";
    }

    @RequestMapping("/error")
    @ExceptionHandler(Throwable.class)
    public String handleError(HttpServletRequest request, Exception e,
                              Model model, SecurityContextHolder holder) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error_404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                return "error/error_505";
            }

        }
        return "error";
    }
}

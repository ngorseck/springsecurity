package sn.minfinances.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login () {

        return "login";
    }
    @RequestMapping("/")
    public String index () {

        return "redirect:/logon";
    }
    @RequestMapping("")
    public String home () {

        return "redirect:/logon";
    }
    @RequestMapping("/logon")
    public String logon (HttpServletRequest req, HttpServletResponse resp) {

        return "redirect:/accueil";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
    @RequestMapping("/403")
    public String error403 () {

        return "403";
    }
}

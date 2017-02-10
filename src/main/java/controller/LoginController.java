package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class LoginController extends HttpServlet{
    @RequestMapping("/login")
    public String login(){


        return "login";
    }
}

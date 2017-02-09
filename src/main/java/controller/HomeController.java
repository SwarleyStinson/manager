package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;

@Controller
public class HomeController extends HttpServlet{

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String gateway() {
        return "home";
    }

}

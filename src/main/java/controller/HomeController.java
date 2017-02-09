package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class HomeController extends HttpServlet{

    @RequestMapping(value = {"/", "/home"})
    public String gateway() {
        return "/WEB-INF/home.jsp";
    }

}

package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class CentralController extends HttpServlet{

    @RequestMapping(value = {"/", "/home"})
    public String gateway() {


        return "home";
    }
    @RequestMapping("/login")
    public String login(){


        return "login";
    }

    @RequestMapping("/hello")
    public String hello(){


        return "/hello/hello";
    }

    @RequestMapping("/AllHello")
    public String allHello(){


        return "/hello/AllHello";
    }

    @RequestMapping("/AdminHello")
    public String AdminHello(){


        return "/hello/admin/AdminHello";
    }


}

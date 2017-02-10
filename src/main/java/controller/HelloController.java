package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class HelloController extends HttpServlet{

    @RequestMapping("/hello")
    public String hello(){


        return "hello/hello";
    }

    @RequestMapping("/AllHello")
    public String allHello(){


        return "hello/AllHello";
    }

    @RequestMapping("/AdminHello")
    public String AdminHello(){


        return "hello/admin/AdminHello";
    }


}

package ru.stepanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.stepanov.dao.ClientDAO;
import ru.stepanov.entity.Client;

import java.sql.SQLException;

@Controller
public class MainController {
    @Autowired
    ClientDAO clientDAO;

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(clientDAO.getAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.POST)
    public ModelAndView admin(@RequestParam(value = "deleteByID", defaultValue = "0") int deleteByID,
                              @RequestParam(value = "isUpdate", defaultValue = "0") int isUpdate,
                              @RequestParam(value = "isCreate", defaultValue = "0") int isCreate,
                              Client client) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();

        if (isUpdate == 1) {
            System.out.println("--------: " + clientDAO.setClientByID(client, client.getId()));
        }
        if (isCreate == 1) {
            System.out.println("--------: " + clientDAO.addClient(client));
        }
        if (deleteByID > 0) {
            System.out.println("--------: " + clientDAO.deleteClientByID(deleteByID));
        }

        //add handler for Bank and Order

        modelAndView.addObject(clientDAO.getAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "This is Hello Page");
        model.addObject("message", "Hello, Cardpay's developer!");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        model.addObject("message", "This is Login page!");
        model.setViewName("login");

        return model;
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is entity
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;
    }
}

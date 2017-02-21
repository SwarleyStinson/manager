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
import ru.stepanov.dao.BankDAO;
import ru.stepanov.dao.ClientDAO;
import ru.stepanov.entity.Bank;
import ru.stepanov.entity.Client;

import java.sql.SQLException;

@Controller
public class MainController {
    @Autowired
    ClientDAO clientDAO;
    @Autowired
    BankDAO bankDAO;

    @RequestMapping(value = "/client**")
    public ModelAndView admin(@RequestParam(value = "deleteByID", defaultValue = "0") int deleteByID,
                              @RequestParam(value = "isUpdate", defaultValue = "0") int isUpdate,
                              @RequestParam(value = "isCreate", defaultValue = "0") int isCreate,
                              @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
                              Client client) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        // Client Database processing
        if (isUpdate == 1) clientDAO.setClientByID(client, client.getId());
        if (isCreate == 1) clientDAO.addClient(client);
        if (deleteByID > 0) clientDAO.deleteClientByID(deleteByID);

        // processing  Current Page
        if (currentPage != 0) {
            if (currentPage == 1) modelAndView.addObject(clientDAO.getPage(clientDAO.getCurrentPageNumber(1)));
            if (currentPage == 2) modelAndView.addObject(clientDAO.getPage(clientDAO.getCurrentPageNumber(2)));
            if (currentPage == 3) modelAndView.addObject(clientDAO.getPage(clientDAO.getCurrentPageNumber(3)));
            if (currentPage == 4) modelAndView.addObject(clientDAO.getPage(clientDAO.getCurrentPageNumber(4)));

        } else modelAndView.addObject(clientDAO.getLast());
        modelAndView.setViewName("client");
        return modelAndView;
    }

    @RequestMapping(value = "/bank**")
    public ModelAndView bank(@RequestParam(value = "deleteByID", defaultValue = "0") int deleteByID,
                              @RequestParam(value = "isUpdate", defaultValue = "0") int isUpdate,
                              @RequestParam(value = "isCreate", defaultValue = "0") int isCreate,
                              @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
                              Bank bank) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        // Bank Database processing
        if (isUpdate == 1) bankDAO.setBankByID(bank, bank.getId());
        if (isCreate == 1) bankDAO.addBank(bank);
        if (deleteByID > 0) bankDAO.deleteBankByID(deleteByID);

        // processing  Current Page
        if (currentPage != 0) {
            if (currentPage == 1) modelAndView.addObject(bankDAO.getPage(bankDAO.getCurrentPageNumber(1)));
            if (currentPage == 2) modelAndView.addObject(bankDAO.getPage(bankDAO.getCurrentPageNumber(2)));
            if (currentPage == 3) modelAndView.addObject(bankDAO.getPage(bankDAO.getCurrentPageNumber(3)));
            if (currentPage == 4) modelAndView.addObject(bankDAO.getPage(bankDAO.getCurrentPageNumber(4)));

        } else modelAndView.addObject(bankDAO.getLast());
        modelAndView.setViewName("bank");
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

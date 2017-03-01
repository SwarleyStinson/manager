package ru.stepanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.stepanov.db.dao.ClientDao;
import ru.stepanov.db.domain.Client;

import java.sql.SQLException;

@Controller
public class ClientController {

    @Autowired
    ClientDao clientDao;

    @RequestMapping(value = "/client**")
    public ModelAndView admin(@RequestParam(value = "deleteById", defaultValue = "0") int deleteClient,
                              @RequestParam(value = "isUpdate", defaultValue = "0") int isUpdate,
                              @RequestParam(value = "isCreate", defaultValue = "0") int isCreate,
                              @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
                              Client client) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        // Client Database processing
        if (isUpdate == 1) clientDao.updateClient(client);
        if (isCreate == 1) clientDao.insertClient(client);
        if (deleteClient > 0) clientDao.deleteClientByID(deleteClient);

        // processing  Current Page
        int page = currentPage > 0 && currentPage < 5 ? clientDao.getCurrentPageNumber(currentPage) : clientDao.getCurrentPage();

        modelAndView.addObject(clientDao.getPage(page));
        modelAndView.setViewName("client");
        return modelAndView;
    }
}

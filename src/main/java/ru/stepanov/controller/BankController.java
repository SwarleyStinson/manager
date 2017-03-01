package ru.stepanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.stepanov.db.dao.BankDAO;
import ru.stepanov.db.domain.Bank;

import java.sql.SQLException;

@Controller
public class BankController {

    @Autowired
    BankDAO bankDAO;

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
        int page = currentPage > 0 && currentPage < 5 ? bankDAO.getCurrentPageNumber(currentPage) : bankDAO.getCurrentPage();

        modelAndView.addObject(bankDAO.getPage(page));
        modelAndView.setViewName("bank");
        return modelAndView;
    }
}

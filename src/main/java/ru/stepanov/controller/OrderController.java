package ru.stepanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.stepanov.db.dao.OrderDao;
import ru.stepanov.db.domain.Order;

import java.sql.SQLException;

@Controller
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @RequestMapping(value = "/orders**")
    public ModelAndView bank(@RequestParam(value = "deleteByID", defaultValue = "0") int deleteByID,
                             @RequestParam(value = "isUpdate", defaultValue = "0") int isUpdate,
                             @RequestParam(value = "isCreate", defaultValue = "0") int isCreate,
                             @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
                             Order order) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        // Order Database processing
        if (isUpdate == 1) orderDao.updateOrder(order);
        if (isCreate == 1) orderDao.insertOrder(order);
        if (deleteByID > 0) orderDao.deleteOrderByID(deleteByID);

        // processing  Current Page
        if (currentPage != 0) {
            if (currentPage == 1) modelAndView.addObject(orderDao.getPage(orderDao.getCurrentPageNumber(1)));
            if (currentPage == 2) modelAndView.addObject(orderDao.getPage(orderDao.getCurrentPageNumber(2)));
            if (currentPage == 3) modelAndView.addObject(orderDao.getPage(orderDao.getCurrentPageNumber(3)));
            if (currentPage == 4) modelAndView.addObject(orderDao.getPage(orderDao.getCurrentPageNumber(4)));

        } else modelAndView.addObject(orderDao.getPage(orderDao.getCurrentPage()));
        modelAndView.setViewName("orders");
        return modelAndView;
    }
}

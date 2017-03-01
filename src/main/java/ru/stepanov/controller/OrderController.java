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
    public ModelAndView handler(@RequestParam(value = "deleteById", defaultValue = "0") int deleteById,
                                @RequestParam(value = "isUpdate", defaultValue = "0") int isUpdate,
                                @RequestParam(value = "isCreate", defaultValue = "0") int isCreate,
                                @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
                                Order order) throws SQLException, ClassNotFoundException {

        // Client Database processing
        if (isUpdate == 1) orderDao.updateOrder(order);
        if (isCreate == 1) orderDao.insertOrder(order);
        if (deleteById > 0) orderDao.deleteOrderByID(deleteById);

        ModelAndView modelAndView = new ModelAndView();

        int page = currentPage > 0 && currentPage < 5 ? orderDao.getCurrentPageNumber(currentPage) : orderDao.getCurrentPage();

        modelAndView.addObject(orderDao.getPage(page));
        modelAndView.setViewName("orders");
        return modelAndView;
    }

}
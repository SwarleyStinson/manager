package ru.stepanov.db.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.stepanov.db.domain.Order;

import java.util.List;

@Repository
public class OrderDao extends AbstractMyBatisDao {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private int tableSize = 0;
    private int currentPage = 0;
    private int maxPage = 0;

    public List<Order> getAllOrders() {
        log.debug("OrderDao.getAllOrders: {}", this.namespace);

        return this.sqlSession.selectList("getAll");
    }

    public void deleteOrderByID(int id) {
        this.sqlSession.delete("deleteOrder", id);
        tableSize--;
        maxPage = getCurrentPageNumber(4);
    }

    public void insertOrder(Order order) {
        this.sqlSession.insert("insertOrder", order);
        tableSize++;
        maxPage = getCurrentPageNumber(4);
    }

    public void updateOrder(Order order) {
        this.sqlSession.update("updateOrder", order);
    }

    public int getCurrentPage() {
        if (tableSize == 0) tableSize = getAllOrders().size();
        if (currentPage == 0) currentPage = getCurrentPageNumber(4);
        if (maxPage == 0) maxPage = currentPage;
        return currentPage;
    }

    public int getCurrentPageNumber(int changePageCommand) {
        if (changePageCommand == 1) currentPage = 1;
        if (changePageCommand == 2)
            if (currentPage > 1) --currentPage;
        if (changePageCommand == 3)
            if (currentPage < maxPage) ++currentPage;
        if (changePageCommand == 4) {
            if (tableSize % 10 != 0) {
                currentPage = tableSize / 10 + 1;
            } else currentPage = tableSize / 10;
        }
        return currentPage;
    }

    public List<Order> getPage(int currentPageNumber) {
        List<Order> result = getAllOrders();

        //удаление с начала до текущей страницы
        int count = 1;
        while (count < currentPage) {
            for (int i = 0; i < 10; i++) result.remove(0);
            count++;
        }
        //удаление до конца
        int cycleEnd = result.size() - 1;

        for (int i = cycleEnd; i >= 10; i--)
            result.remove(i);

        return result;
    }
}

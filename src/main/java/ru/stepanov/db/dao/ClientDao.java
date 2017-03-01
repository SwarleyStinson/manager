package ru.stepanov.db.dao;

import org.springframework.stereotype.Repository;
import ru.stepanov.db.domain.Client;

import java.util.List;

@Repository
//@MyBatisDao("ClientDao")
public class ClientDao extends AbstractMyBatisDao {
    private int tableSize = 0;
    private int currentPage = 0;
    private int maxPage = 0;

    public List<Client> getAllClient() {

        return this.sqlSession.selectList("getAllClient");
    }

    public void deleteClientByID(int id){
        this.sqlSession.delete("deleteClientById", id);
        tableSize--;
        maxPage = getCurrentPageNumber(4);
    }

    public void insertClient(Client client) {
        this.sqlSession.insert("insertClient", client);
        tableSize++;
        maxPage = getCurrentPageNumber(4);
    }

    public void updateClient(Client client) {
        this.sqlSession.update("updateClient", client);
    }

    public int getCurrentPage() {
        if(tableSize == 0) tableSize = getAllClient().size();
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

    public List<Client> getPage(int currentPageNumber) {
        List<Client> result = getAllClient();

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
